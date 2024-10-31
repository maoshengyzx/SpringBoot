package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: TopicRabbitConfig
 * Package: com.example.springbootrabbitmq.config
 * Description:
 * 主题交换机配置类
 *
 * @Author ms
 * @Create 2024/10/31 19:34
 * @Version 1.0
 */

@Configuration
public class TopicRabbitConfig {

    private static final String man = "topic.man";

    private static final String woman = "topic.woman";

    /**
     * 创建主题队列
     *
     * @return
     */
    @Bean
    public Queue topicQueueFirst() {
        return new Queue("topicQueueFirst", true);
    }

    @Bean
    public Queue topicQueueSecond() {
        return new Queue("topicQueueSecond", true);
    }


    /**
     * 创建主题交换机
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange", true, false);
    }


    /**
     * 将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
     * 这样只要是消息携带的路由键是topic.man,才会分发到该队列
     */
    @Bean
    public Binding topicBindingFirst() {
        return BindingBuilder.bind(topicQueueFirst()).to(topicExchange()).with(man);
    }


    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    public Binding topicBindingSecond() {
        return BindingBuilder.bind(topicQueueSecond()).to(topicExchange()).with("topic.#");
    }

}
