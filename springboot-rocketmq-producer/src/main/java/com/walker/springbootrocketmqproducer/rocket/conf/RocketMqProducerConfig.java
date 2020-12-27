package com.walker.springbootrocketmqproducer.rocket.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Walker
 * @date 2020/12/17 2:24 下午
 */
@Configuration
@Slf4j
public class RocketMqProducerConfig {

    @Value("${rocketMq.producer.groupName}")
    private String groupName;
    /**
     * 服务器地址，若为多个使用逗号分隔
     */
    @Value("${rocketMq.base.nameSrvAddr}")
    private String nameSrvAddr;
    /**
     * 消息最大大小，默认4M
     */
    @Value("${rocketMq.base.maxMessageSize}")
    private Integer maxMessageSize;
    /**
     * 消息发送超时时间，默认3秒
     */
    @Value("${rocketMq.base.sendMsgTimeout}")
    private Integer sendMsgTimeout;
    /**
     * 消息发送失败重试次数，默认2次
     */
    @Value("${rocketMq.base.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;

    /**
     * 获取生产者
     *
     * @return
     */
    @Bean
    public DefaultMQProducer getRocketMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(nameSrvAddr);
        //消息最大大小，默认4M
        if (maxMessageSize != null) {
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        //消息发送超时时间，默认3秒
        if (sendMsgTimeout != null) {
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if (retryTimesWhenSendFailed != null) {
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }
        try {
            producer.start();
        } catch (MQClientException e) {
            log.error("rocket mq producer start error, ", e);
        }
        return producer;
    }
}
