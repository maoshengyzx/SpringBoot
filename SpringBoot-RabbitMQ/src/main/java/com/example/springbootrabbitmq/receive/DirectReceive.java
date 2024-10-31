package com.example.springbootrabbitmq.receive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ClassName: DirectRecept
 * Package: com.example.springbootrabbitmq.recept
 * Description:
 *
 * @Author ms
 * @Create 2024/10/30 22:21
 * @Version 1.0
 */
@Component
public class DirectReceive {

    private final static Logger LOGGER = LoggerFactory.getLogger(DirectReceive.class);

    /**
     * 接收RabbitMQ消息
     * @param testMessage
     */
    @RabbitListener(queuesToDeclare = @Queue("directQueue"))
    public void  receiveMessageFirst(Map<String,Object> testMessage){
        LOGGER.info("第一个接收消息为：{}",testMessage);
    }

    /**
     * 接收RabbitMQ消息
     *
     * @param testMessage
     */
    @RabbitListener(queuesToDeclare = @Queue("directQueue"))
    public void receiveMessageSecond(Map<String, Object> testMessage) {
        LOGGER.info("第二个接收消息为：{}", testMessage);
    }
}
