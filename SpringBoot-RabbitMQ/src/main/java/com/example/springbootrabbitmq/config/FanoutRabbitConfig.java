package com.example.springbootrabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FanoutRabbitConfig
 * Package: com.example.springbootrabbitmq.config
 * Description:
 * 扇形交互机配置类
 *
 * @Author ms
 * @Create 2024/10/31 19:51
 * @Version 1.0
 */

@Configuration
public class FanoutRabbitConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(FanoutRabbitConfig.class);


    /**
     * 创建扇形交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange", true, false);
    }

    @Bean
    public FanoutExchange canalFanoutExchange() {
        return new FanoutExchange("canalFanoutExchange", true, false);
    }

    /**
     * 创建队列
     */
    @Bean
    public Queue fanoutQueueFirst() {
        return new Queue("fanoutQueueFirst", true);
    }

    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    public Queue fanoutQueueSecond() {
        return new Queue("fanoutQueueSecond", true);
    }

    @Bean
    public Queue canalQueue() {
        return new Queue("canalQueue", true);
    }

    /**
     * 对于扇形交换机  routingKey  不用配置  因为扇形交换机  不需要路由键
     *
     * @return
     */
    @Bean
    public Binding fanoutBindingFirst() {
        LOGGER.info("绑定扇形交换机1");
        return BindingBuilder.bind(fanoutQueueFirst()).to(fanoutExchange());
    }

    /**
     * 对于扇形交换机  routingKey  不用配置  因为扇形交换机  不需要路由键
     *
     * @return
     */
    @Bean
    public Binding fanoutBindingSecond() {
        LOGGER.info("绑定扇形交换机2");
        return BindingBuilder.bind(fanoutQueueSecond()).to(fanoutExchange());
    }

    @Bean
    public Binding canalBinding() {
        LOGGER.info("绑定canal交换机");
        return BindingBuilder.bind(canalQueue()).to(canalFanoutExchange());
    }

}
