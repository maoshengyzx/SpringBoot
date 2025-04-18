package com.example.springbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Service
public class KafkaMessageConsumer {

    private static Logger log = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    private final Map<String, BlockingQueue<ConsumerRecord<String, String>>> priorityQueues = Map.of(
            "high", new LinkedBlockingQueue<>(64),
            "medium", new LinkedBlockingQueue<>(64),
            "low", new LinkedBlockingQueue<>(64)
    );

    /**
     * 测试消费模式
     *
     * @param records
     */
    @KafkaListener(topics = "test-messages", containerFactory = "kafkaListenerContainerFactory", groupId = "test-group")
    public void receiveAdminMessage(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        log.info("消费者1开始消费，{}", records.size());
        // 按优先级分组
        Map<String, List<ConsumerRecord<String, String>>> priorityGroups = records.stream()
                .collect(Collectors.groupingBy(record -> {
                    Header header = record.headers().lastHeader("priority");
                    return header != null ? new String(header.value()) : "low";
                }));

        // 先处理高优先级
        processPriorityGroup("high", priorityGroups.getOrDefault("high", Collections.emptyList()));

        // 处理中优先级
        processPriorityGroup("medium", priorityGroups.getOrDefault("medium", Collections.emptyList()));

        // 处理低优先级
        processPriorityGroup("low", priorityGroups.getOrDefault("low", Collections.emptyList()));

        ack.acknowledge();
        log.info("已提交偏移量到: {}", records.get(records.size() - 1).offset());
        log.info("消费者1完成消费");
    }

    private void processPriorityGroup(String priority, List<ConsumerRecord<String, String>> highConsumerRecords) {
        if (CollectionUtils.isEmpty(highConsumerRecords)) {
            return;
        }
        log.info("优先级:{}", priority);
        for (ConsumerRecord<String, String> record : highConsumerRecords) {
            log.info("value:{}", record.value());
        }
        log.info("优先级:{} 完成消费", priority);
    }


    /*    *//**
     * 测试消费模式
     *
     * @param record
     *//*
    @KafkaListener(topics = "admin-messages", groupId = "admin-group")
    public void receiveAdminMessage2(ConsumerRecord<String, String> record) {
        log.info("消费者2开始消费，{}", record);
        JSONObject jsonObject = JSONUtil.parseObj(record.value());
        log.info("消费者2消费成功");
    }

    *//**
     * 测试消费模式
     *
     * @param record
     *//*
    @KafkaListener(topics = "admin-messages", groupId = "admin-group")
    public void receiveAdminMessage3(ConsumerRecord<String, String> record) {
        log.info("消费者3开始消费，{}", record);
        JSONObject jsonObject = JSONUtil.parseObj(record.value());
        log.info("消费者3消费成功");
    }

    */

    /**
     * 测试消费模式
     *
     * @param record
     *//*
    @KafkaListener(topics = "admin-messages", groupId = "admin-group1")
    public void receiveAdminMessage4(ConsumerRecord<String, String> record) {
        log.info("消费者4开始消费，{}", record);
        JSONObject jsonObject = JSONUtil.parseObj(record.value());
        log.info("消费者4完成消费");
    }*/
    private String extractPriority(ConsumerRecord<String, String> record) {
        Header header = record.headers().lastHeader("priority");
        return header != null ? new String(header.value()) : "low";
    }
}