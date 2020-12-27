package com.walker.springbootrocketmqproducer.rocket.service;

import com.walker.springbootrocketmqproducer.rocket.conf.RocketMqProducerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Walker
 * @date 2020/12/17 2:26 下午
 */
@Service
@Slf4j
public class RocketMqProducerService {

    /**
     * topic名称
     */
    @Value("${rocketMq.producer.topicName}")
    private String topicName;
    /**
     * tags
     */
    @Value("${rocketMq.producer.tags}")
    private String tags;

    @Autowired
    private RocketMqProducerConfig rocketMqProducerConfig;

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        DefaultMQProducer defaultMQProducer = rocketMqProducerConfig.getRocketMQProducer();
        Message sendMsg = new Message(topicName, tags, msg.getBytes());
        try {
            if (null != defaultMQProducer) {
                SendResult result = defaultMQProducer.send(sendMsg);
                if (!result.getSendStatus().equals(SendStatus.SEND_OK)) {
                    log.error("send msg to api delete local cache fail msg: {},result : {}", msg, result);
                }
            }
        } catch (Exception e) {
            log.error("send msg error, ", e);
        }
    }
}
