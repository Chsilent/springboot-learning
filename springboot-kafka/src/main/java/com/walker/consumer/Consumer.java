package com.walker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka consumer
 *
 * @author Walker
 * @date 2019/3/12 上午10:03
 */
@Component
public class Consumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 一个消费组消费多个topic
     * @param record
     */
    /*@KafkaListener(topics = {"topic1","topic2"}, groupId = "walkerTest")
    public void listener(ConsumerRecord<String, Object> record) {
        logger.info("消费者接收消息: " + record);

        System.out.printf("topic = %s, partition = %d, offset = %d,key = %s, value = %s \n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }*/
}
