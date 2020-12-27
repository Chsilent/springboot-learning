package com.walker.springbootrocketmqconsumer.rocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Walker
 * @date 2020/12/17 5:17 下午
 */
@Component
@Slf4j
public abstract class AbstractRocketMqConsumer {

    /**
     * 实际处理消息的有消费方具体实现
     *
     * @param msgList
     * @return
     */
    public abstract ConsumeConcurrentlyStatus doConsumerMsg(List<MessageExt> msgList);
}
