package com.example.springbootkafka.services;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.springbootkafka.utils.SystemException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;


@Service
public class KafkaMessageReaderService {

    private static final Log log = LogFactory.get();

    private final DefaultKafkaConsumerFactory<String, String> consumerFactory;

    private final KafkaAdmin kafkaAdmin;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageReaderService(DefaultKafkaConsumerFactory<String, String> consumerFactory, KafkaAdmin kafkaAdmin, KafkaTemplate<String, String> kafkaTemplate) {
        this.consumerFactory = consumerFactory;
        this.kafkaAdmin = kafkaAdmin;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 获取指定分区的最后消费消息的完整元数据
     */
    public Map<String, Object> getLastMessageFullMetadata(String topic, int partition, String groupId) {
        try (AdminClient admin = AdminClient.create(consumerFactory.getConfigurationProperties())) {
            // 获取消费者组偏移量
            Map<TopicPartition, OffsetAndMetadata> offsets = admin.listConsumerGroupOffsets(groupId)
                    .partitionsToOffsetAndMetadata()
                    .get();

            TopicPartition tp = new TopicPartition(topic, partition);
            if (!offsets.containsKey(tp)) {
                return Collections.emptyMap();
            }

            long lastConsumedOffset = offsets.get(tp).offset() - 1;
            if (lastConsumedOffset < 0) {
                return Collections.emptyMap();
            }

            // 使用消费者获取具体消息
            try (Consumer<String, String> consumer = consumerFactory.createConsumer(groupId, null)) {
                consumer.assign(Collections.singletonList(tp));
                consumer.seek(tp, lastConsumedOffset);

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                return records.isEmpty() ? Collections.emptyMap() : extractFullMetadata(records.iterator().next());
            }
        } catch (Exception e) {
            throw SystemException.wrap(e);
        }
    }

    private String getPriorityFromHeaders(ConsumerRecord<String, String> record) {
        Header header = record.headers().lastHeader("priority");
        return header != null ? new String(header.value()) : "low";
    }

    /**
     * 提取消息的完整元数据
     */
    private Map<String, Object> extractFullMetadata(ConsumerRecord<String, String> record) {
        Map<String, Object> metadata = new LinkedHashMap<>();

        // 1. 基础元数据
        metadata.put("offset", record.offset());
        metadata.put("partition", record.partition());
        metadata.put("timestamp", record.timestamp());
        metadata.put("timestampType", record.timestampType().name());
        metadata.put("key", record.key());
        metadata.put("value", record.value());

        // 2. 大小信息
        metadata.put("keySizeBytes", record.serializedKeySize());
        metadata.put("valueSizeBytes", record.serializedValueSize());
        metadata.put("headersSizeBytes", calculateHeadersSize(record.headers()));
        metadata.put("totalSizeBytes", record.serializedKeySize() + record.serializedValueSize()
                + calculateHeadersSize(record.headers()));


        // 3. Headers信息
        metadata.put("headers", extractHeaders(record.headers()));

        // 4. 使用AdminClient获取Broker相关信息
        try (AdminClient admin = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            // 获取Leader Broker信息
            TopicDescription topicDesc = admin.describeTopics(Collections.singletonList(record.topic()))
                    .topicNameValues().get(record.topic()).get();
            metadata.put("leaderBrokerId",
                    topicDesc.partitions().get(record.partition()).leader().id());

            // 获取ISR副本信息
            metadata.put("isrBrokers",
                    topicDesc.partitions().get(record.partition()).isr().stream()
                            .map(Node::id)
                            .collect(Collectors.toList()));

            // 获取Topic压缩类型配置
            ConfigResource configResource = new ConfigResource(
                    ConfigResource.Type.TOPIC, record.topic());
            Config config = admin.describeConfigs(Collections.singletonList(configResource))
                    .values().get(configResource).get();
            metadata.put("compressionType", config.get("compression.type").value());
        } catch (Exception e) {
            metadata.put("adminClientError", "Failed to fetch broker info: " + e.getMessage());
        }

        // 5. 序列化格式（从ConsumerFactory获取）
        metadata.put("keyDeserializer",
                kafkaTemplate.getProducerFactory().getConfigurationProperties().get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));

        metadata.put("valueDeserializer",
                kafkaTemplate.getProducerFactory().getConfigurationProperties().get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));

        // 6. 从headers中提取业务元数据
        metadata.putAll(extractBusinessMetadataFromHeaders(record.headers()));

        return metadata;
    }

    /**
     * 计算headers的总大小
     */
    private int calculateHeadersSize(Headers headers) {
        int size = 0;
        for (Header header : headers) {
            size += header.key().getBytes(StandardCharsets.UTF_8).length;
            size += header.value() != null ? header.value().length : 0;
        }
        return size;
    }

    /**
     * 提取headers为可读格式
     */
    private Map<String, String> extractHeaders(Headers headers) {
        Map<String, String> headerMap = new HashMap<>();
        headers.forEach(header ->
                headerMap.put(header.key(),
                        header.value() != null ? new String(header.value()) : "null"));
        return headerMap;
    }

    /**
     * 从headers中提取业务相关元数据
     */
    private Map<String, Object> extractBusinessMetadataFromHeaders(Headers headers) {
        Map<String, Object> businessMetadata = new HashMap<>();


        Object retryCount = kafkaTemplate.getProducerFactory().getConfigurationProperties().get(ProducerConfig.RETRIES_CONFIG);
        log.info("retryCount:{}", retryCount);

        // 消息来源
        Header sourceHeader = headers.lastHeader("producer-id");
        if (sourceHeader != null) {
            businessMetadata.put("source", new String(sourceHeader.value()));
        }

        // 重试次数
        businessMetadata.put("retryCount", retryCount);

        // 序列化格式（如果生产者通过header传递）
        Header serHeader = headers.lastHeader("serialization-format");
        if (serHeader != null) {
            businessMetadata.put("serializationFormat", new String(serHeader.value()));
        }

        return businessMetadata;
    }

    // 辅助方法：格式化消息头
    private String formatHeaders(Headers headers) {
        StringBuilder sb = new StringBuilder();
        headers.forEach(header -> {
            sb.append(header.key()).append(": ")
                    .append(new String(header.value())).append("; ");
        });
        return sb.toString().isEmpty() ? "none" : sb.toString();
    }
}