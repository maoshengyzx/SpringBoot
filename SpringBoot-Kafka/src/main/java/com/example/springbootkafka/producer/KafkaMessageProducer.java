package com.example.springbootkafka.producer;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.springbootkafka.entry.Test;
import com.example.springbootkafka.priority_enum.Priority;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageProducer {

    private static final String TOPIC = "admin-messages";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendAdminMessage(String topic, int partition, List<Test> message) {
        List<ProducerRecord<String, String>> records = new ArrayList<>();
        for (Test test : message) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, partition, IdUtil.fastSimpleUUID(), JSONUtil.toJsonStr(test));
            Priority priorityEnum = Priority.fromValue(test.getPriority());
            try {
                record.headers().add("priority", priorityEnum.getValue().getBytes(StandardCharsets.UTF_8))
                        .add("sourceIp", InetAddress.getLocalHost().getHostAddress().getBytes(StandardCharsets.UTF_8));
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            records.add(record);
        }
        for (ProducerRecord<String, String> record : records) {
            kafkaTemplate.send(record);
        }
    }

    /**
     * 获取 历史消息
     *
     * @param topic
     * @param partition
     * @param offset
     * @return
     */
    public String getHistoryMessage(String topic, int partition, long offset) {
        // TODO: 2024/4/17 实现获取历史消息的逻辑
        ConsumerRecord<String, String> receive = kafkaTemplate.receive(topic, partition, offset, Duration.ofSeconds(1));
        if (receive != null) {
            return receive.value();
        }
        return null;
    }
}