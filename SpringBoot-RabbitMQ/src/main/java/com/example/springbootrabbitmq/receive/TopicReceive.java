package com.example.springbootrabbitmq.receive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ClassName: TopicReceive
 * Package: com.example.springbootrabbitmq.receive
 * Description:
 *
 * @Author ms
 * @Create 2024/10/31 19:47
 * @Version 1.0
 */
@Component
public class TopicReceive {
    private final static Logger LOGGER = LoggerFactory.getLogger(TopicReceive.class);

    @RabbitListener(queues = "topicQueueFirst")
    public void receive1(Map<String,Object> msg) {
        LOGGER.info("topicQueueFirst 接收到消息：" + msg);
    }

    @RabbitListener(queues = "topicQueueSecond")
    public void receive2(Map<String,Object> msg) {
        LOGGER.info("topicQueueSecond 接收到消息：" + msg);
    }
}
