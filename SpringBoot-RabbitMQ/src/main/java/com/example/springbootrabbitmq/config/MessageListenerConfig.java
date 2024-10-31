package com.example.springbootrabbitmq.config;

import com.example.springbootrabbitmq.listener.MyAckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MessageListenerConfig
 * Package: com.example.springbootrabbitmq.config
 * Description:
 * 消息监听配置类  用于配置消息监听  如果不需要配置  只需要在接收消息的类上加上 @RabbitListener 注解即可
 *
 * @Author ms
 * @Create 2024/10/31 21:36
 * @Version 1.0
 */
@Configuration
public class MessageListenerConfig {

    private final CachingConnectionFactory cachingConnectionFactory;

    private final MyAckReceiver myAckReceiver;

    public MessageListenerConfig(CachingConnectionFactory cachingConnectionFactory, MyAckReceiver myAckReceiver) {
        this.cachingConnectionFactory = cachingConnectionFactory;
        this.myAckReceiver = myAckReceiver;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(cachingConnectionFactory);
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(1);

        // 设置确认模式  手动确认
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setQueueNames("directQueue");

        simpleMessageListenerContainer.setMessageListener(myAckReceiver);
        return simpleMessageListenerContainer;
    }
}
