package com.example.springbootkafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yyb on 2018/12/12.
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    /**
     * kafka地址
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    /**
     * 默认组
     */
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    /**
     * 自动消费设定
     */
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    /**
     * 一次批量处理的数据量
     */
    @Value("${spring.kafka.consumer.max-poll-records}")
    private int maxPollRecords;
    /**
     * 一次批量处理的时间间隔
     */
    @Value("${spring.kafka.consumer.max-poll-interval-ms}")
    private int maxPollIntervalMs;

    @Value("${spring.kafka.listener.concurrency}")
    private int cocurrency;

    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private Integer commitInterval;

    /**
     * 配置批量消费，kafka默认为单条消费，需要配置批量功能才能批量消费
     *
     * @return
     */
    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String,String> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(cocurrency);
        //设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.setBatchListener(true);
        // 此参数必须设置才能手动提交偏移量
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }

    @Bean
    public DefaultKafkaConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, commitInterval);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        //每一批数量
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return props;
    }


    @Bean
    public Map<String, Object> produceConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 20);
        // 消息偏移量增量设置

        return props;
    }

    @Bean
    public DefaultKafkaProducerFactory producerFactory() {
        return new DefaultKafkaProducerFactory<>(produceConfigs());
    }


    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setConsumerFactory(consumerFactory());
        return kafkaTemplate;
    }

}