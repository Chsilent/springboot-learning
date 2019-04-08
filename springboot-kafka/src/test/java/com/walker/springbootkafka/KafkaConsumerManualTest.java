package com.walker.springbootkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * kafka消费者手动分区测试
 *
 * @author Walker
 * @date 2019/3/14 上午10:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaConsumerManualTest {

    @Test
    public void threadConsumer() throws InterruptedException {

        String topic = "topic1";
        Properties properties = setProperties("127.0.0.1:9092", "walkerTest3");

        //创建消费者1指定分区0
        TopicPartition partition0 = new TopicPartition(topic, 0);
        Consumer consumer1 = new Consumer(partition0, properties, "consumer1");

        //创建消费者1指定分区1
        TopicPartition partition1 = new TopicPartition(topic, 1);
        Consumer consumer2 = new Consumer(partition1, properties, "consumer2");

        //创建消费者1指定分区2
        TopicPartition partition2 = new TopicPartition(topic, 2);
        Consumer consumer3 = new Consumer(partition2, properties, "consumer3");

        //创建消费者1指定分区0
        TopicPartition partition3 = new TopicPartition(topic, 3);
        Consumer consumer4 = new Consumer(partition3, properties, "consumer4");

        //创建消费者5指定分区0
        TopicPartition partition4 = new TopicPartition(topic, 4);
        Consumer consumer5 = new Consumer(partition4, properties, "consumer5");

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();

        consumer1.join();
        consumer2.join();
        consumer3.join();
        consumer4.join();
        consumer5.join();

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
        //手动分区时，不会进行rebalance 消费分区的分配策略RangeAssignor/RoundRobinAssignor
        //properties.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RangeAssignor");
        properties.put("max.poll.records", "100");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    /**
     * 消费者线程类
     */
    private class Consumer extends Thread {

        /**
         * 主题分区
         */
        private TopicPartition topicPartition;

        /**
         * 配置属性
         */
        private Properties properties;

        /**
         * kafka消费者
         */
        private KafkaConsumer<String, String> consumer;

        /**
         * 分区列表
         */
        private List<TopicPartition> topicPartitions = new ArrayList<>();

        /**
         * 构造函数
         *
         * @param topicPartition
         * @param properties
         * @param name
         */
        public Consumer(TopicPartition topicPartition, Properties properties, String name) {
            super(name);
            this.topicPartition = topicPartition;
            this.properties = properties;
            consumer = new KafkaConsumer<String, String>(properties);
            topicPartitions.add(topicPartition);
        }

        @Override
        public void run() {
            //手动分配分区，当topicPartitions为空时，相当于是unsubscribe()
            consumer.assign(topicPartitions);
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(101));
                for (ConsumerRecord<String, String> record : records) {

                    System.out.println(Thread.currentThread().getName() + "---" + record.topic() + "---" + record.partition() + "---" + record.offset() + "---" + record.key() + "---" + record.value());
                    //消费完消息手动提交
                    consumer.commitAsync();
                }
            }
        }
    }
}
