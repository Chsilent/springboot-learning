package com.walker.springbootkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * kafka消费者自动分区测试
 *
 * @author Walker
 * @date 2019/3/14 上午11:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaConsumerAutoTest {

    /**
     * 测试多线程消费
     */
    @Test
    public void threadConsumer() {
        String broker = "127.0.0.1:9092";
        String groupId = "walkerTest1";
        String topic = "topic1";
        Properties properties = setProperties(broker, groupId);
        /*int size = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            ConsumerAuto consumer = new ConsumerAuto(properties, topic);
            executorService.submit(consumer);
        }*/
        ConsumerAuto consumerAuto = new ConsumerAuto(properties, topic);
        consumerAuto.start();


    }

    /**
     * kafka消费者相关属性配置
     *
     * @param brokers
     * @param groupId
     * @return
     */
    public Properties setProperties(String brokers, String groupId) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokers);
        properties.put("group.id", groupId);
        properties.put("enable.auto.commit", "false");
        properties.put("session.timeout.ms", "30000");
        properties.put("auto.commit.interval.ms", "1000");
        //指定消费者在读取一个没有偏移量的分区或者偏移量无效的情况下如何处理，earliest和latest分别表示最早和最近的
        properties.put("auto.offset.reset", "earliest");
        //消费分区的分配策略RangeAssignor/RoundRobinAssignor
        properties.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RangeAssignor");
        properties.put("max.poll.records", "100");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    /**
     * 消费者线程类
     */
    private class ConsumerAuto extends Thread {

        /**
         * kafka消费者
         */
        private KafkaConsumer<String, String> consumer;

        /**
         * 构造
         *
         * @param properties
         * @param topic
         */
        public ConsumerAuto(Properties properties, String topic) {
            consumer = new KafkaConsumer<String, String>(properties);
            //订阅主题，目的-每个消费者线程通过分区策略自动实现主题的分区消费
            consumer.subscribe(Collections.singleton(topic));
        }

        @Override
        public void run() {

            while (true) {
                System.out.println("执行了该方法");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(99));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(Thread.currentThread().getName() + "---" + record.topic() + "---" + record.partition() + "---" + record.offset() + "---" + record.key() + "---" + record.value());
                    //消费完消息手动提交
                    consumer.commitAsync();
                }
            }
        }
    }
}
