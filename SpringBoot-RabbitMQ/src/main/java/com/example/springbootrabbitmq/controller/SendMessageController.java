package com.example.springbootrabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: SendMessageController
 * Package: com.example.springbootrabbitmq.controller
 * Description:
 *
 * @Author ms
 * @Create 2024/10/30 22:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/send")
public class SendMessageController {

    private final RabbitTemplate rabbitTemplate;

    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送消息
     */
    @GetMapping("/directMessage")
    public String sendDirectMessage() {
        // 创建要发送的消息
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 发送消息到交换机
        rabbitTemplate.convertAndSend("directExchange", "directRoutingKey", map);
        return "消息发送成功";
    }

    /**
     * 发送主题消息
     *
     * @return
     */
    @GetMapping("/topicMessage")
    public String sendTopicMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 发送消息到交换机
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", map);
        return "消息发送成功";
    }


    /**
     * 向扇形交换机发送消息
     *
     * @return
     */
    @GetMapping("/fanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 发送消息到交换机
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "消息发送成功";
    }


    /**
     * 将消息推送到不存在的交换机上，测试确认回调
     *
     * @return
     */
    @GetMapping("/testMessageAck")
    public String testMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 发送消息到交换机
        rabbitTemplate.convertAndSend("no-exist-exchange", "directRoutingKey", map);
        return "消息发送成功";
    }

    /**
     * 测试交换机存在，但是该交换机没有该路由键的队列，测试确认回调
     *
     * @return
     */
    @GetMapping("/testMessageAck2")
    public String testMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 发送消息到交换机
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "no-exist-routingKey", map);
        return "消息发送成功";
    }
}
