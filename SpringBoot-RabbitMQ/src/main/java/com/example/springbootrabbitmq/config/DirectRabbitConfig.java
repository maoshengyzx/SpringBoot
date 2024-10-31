package com.example.springbootrabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: DirectExchangeConfig
 * Package: com.example.springbootrabbitmq.config
 * Description:  直连交换机配置  一个交换机只能绑定一个队列
 * @Author ms
 * @Create 2024/10/30 21:56
 * @Version 1.0
 */

@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue directQueue(){
        /**
         * 参数说明：
         *  durable：是否持久化  默认为false，持久化队列：会存储到磁盘上，当消息代理重启时依然存在，暂存队列：当前连接有效
         *  autoDelete：是否自动删除  默认为false，当没有生产者或者消费者使用此队列，该队列会自动删除
         *  exclusive：是否排它  默认为false，排它的队列只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
         */

        // 一般只设置 持久化  其他两个默认
        return new Queue("directQueue",true);
    }

    /**
     * 创建直连交换机  起名：directExchange
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange",true,false);
    }

    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }



    /**
     * 绑定队列和交换机
     * @return
     */
    @Bean
    public Binding directBinding(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("directRoutingKey");
    }

}
