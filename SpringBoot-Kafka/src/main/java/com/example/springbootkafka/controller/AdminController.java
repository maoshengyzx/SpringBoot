package com.example.springbootkafka.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.springbootkafka.entry.Test;
import com.example.springbootkafka.producer.KafkaMessageProducer;
import com.example.springbootkafka.services.KafkaMessageReaderService;
import com.example.springbootkafka.utils.AjaxResponseWrapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/message")
public class AdminController {

    private static final Log log = LogFactory.get();

    @Resource
    private KafkaMessageReaderService kafkaMessageReader;


    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;


    @PostMapping("/publish")
    public void publishAdminMessage(@RequestParam String topic,
                                    @RequestParam(required = false, defaultValue = "0") int partition,
                                    @RequestBody List<Test> message) {
        kafkaMessageProducer.sendAdminMessage(topic, partition, message);
    }

    @GetMapping("/getHistoryMessage")
    public AjaxResponseWrapper<Map<String, Object>> getHistoryMessage(@RequestParam String topic, @RequestParam int partition, @RequestParam String groupId) {
        return AjaxResponseWrapper.data(kafkaMessageReader.getLastMessageFullMetadata(topic, partition, groupId));
    }
}