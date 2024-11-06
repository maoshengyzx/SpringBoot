package com.example.springbootrabbitmq.receive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ClassName: FanoutReveive
 * Package: com.example.springbootrabbitmq.receive
 * Description:
 *
 * @Author ms
 * @Create 2024/10/31 20:02
 * @Version 1.0
 */
@Component
public class FanoutReceive {

    private final static Logger LOGGER = LoggerFactory.getLogger(FanoutReceive.class);

    @RabbitListener(queuesToDeclare = @Queue("fanoutQueueFirst"))
    public void receive1(Map<String, Object> msg) {
        LOGGER.info("fanoutQueueFirst：{}", msg);
    }

    @RabbitListener(queuesToDeclare = @Queue("fanoutQueueSecond"))
    public void receive2(Map<String, Object> msg) {
        LOGGER.info("fanoutQueueSecond：{}", msg);
    }

    @RabbitListener(queuesToDeclare = @Queue("canalQueue"))
    public void receive3(@Payload String body, @Headers Map<String,Object> headers) {
//        LOGGER.info("canalQueue：{}", canalEntity);
        LOGGER.info("canalQueueBody：{}", body);
        LOGGER.info("canalQueueHeaders：{}", headers);
    }


}
