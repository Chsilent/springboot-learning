package com.walker.springbootrocketmqconsumer.rocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 消息监听类
 *
 * @author Walker
 * @date 2020/12/17 5:18 下午
 */
@Component
@Slf4j
public class LocalCacheMsgConsumer extends AbstractRocketMqConsumer implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${rocketMq.localCache.consumer.topicName}")
    private String topicName;
    @Value("${rocketMq.localCache.consumer.tags}")
    private String tags;


    @Autowired
    private RocketMqConsumerConfig rocketMqConsumerConfig;

    public DefaultMQPushConsumer consumer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            listener(topicName, tags);
        } catch (MQClientException e) {
            log.error("rocket mq consumer listener init", e);
        }
    }

    /**
     * 开启消费者监听服务
     *
     * @param topic
     * @param tag
     * @throws MQClientException
     */
    public void listener(String topic, String tag) throws MQClientException {
        log.info("----------start rocketMQ consumer listener topic:{}, tag:{}", topic, tag);
        consumer = new DefaultMQPushConsumer(rocketMqConsumerConfig.getGroupName());
        consumer.setNamesrvAddr(rocketMqConsumerConfig.getNameSrvAddr());
        consumer.subscribe(topic, tag);
        //设置消息为广播
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setConsumeMessageBatchMaxSize(rocketMqConsumerConfig.getConsumeMessageBatchMaxSize());
        // 开启内部类实现监听
        consumer.registerMessageListener((MessageListenerConcurrently) (msgList, context) -> doConsumerMsg(msgList));
        consumer.start();
    }

    @Override
    public ConsumeConcurrentlyStatus doConsumerMsg(List<MessageExt> msgList) {
        if (CollectionUtils.isEmpty(msgList)) {
            log.info("consumer msg list is empty!");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        for (MessageExt msg : msgList) {
            try {
                String msgStr = new String(msg.getBody(), "utf-8");
                log.info("receive msg :{}", msgStr);
            } catch (UnsupportedEncodingException e) {
                log.error("msg body change to String error ", e);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /**
     * 删除本地缓存
     *
     * @param msgStr
     */
    private void deleteLocalCache(String msgStr) {

    }
}
