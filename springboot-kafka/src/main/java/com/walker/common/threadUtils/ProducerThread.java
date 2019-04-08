package com.walker.common.threadUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 生产者线程
 *
 * @author Walker
 * @date 2019/3/12 下午3:22
 */
public class ProducerThread implements Runnable {

    private final KafkaTemplate kafkaTemplate;

    private String topic;

    @Autowired
    public ProducerThread(KafkaTemplate kafkaTemplate, String topic) {

        this.kafkaTemplate = kafkaTemplate;

        this.topic = topic;
    }

    @Override
    public void run() {
        //往五个分区分别发送10条消息
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                String message = "message" + String.valueOf(j);
                kafkaTemplate.send(topic, i, "walker"+i, message);
            }
        }
        /*for (int j = 0; j < 10; j++) {
            String message = "message" + String.valueOf(j);
            kafkaTemplate.send(topic, "walker", message);
        }*/
        System.out.println("生产者消息发送完毕!!!!");
    }
}
