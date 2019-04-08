package com.walker.common.threadUtils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 消费者线程
 *
 * @author Walker
 * @date 2019/3/12 下午3:39
 */
public class ConsumerThread implements Runnable {

    private static KafkaConsumer<String, String> kafkaConsumer;

    public ConsumerThread(String brokers, String groupId, String topic) {
        Properties properties = buildKafkaProperty(brokers, groupId);
        kafkaConsumer = new KafkaConsumer<>(properties);
        //订阅主题的全部分区
        kafkaConsumer.subscribe(Collections.singletonList("topic1"));
    }

    /**
     * 相关属性
     *
     * @param brokers
     * @param groupId
     * @return
     */
    private static Properties buildKafkaProperty(String brokers, String groupId) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokers);
        properties.put("group.id", groupId);
        properties.put("enable.auto.commit", "false");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        //指定消费者在读取一个没有偏移量的分区或者偏移量无效的情况下如何处理，earliest和latest分别表示最早和最近的
        properties.put("auto.offset.reset", "earliest");
        //消费分区的分配策略RangeAssignor/RoundRobinAssignor
        properties.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RangeAssignor");
        properties.put("max.poll.records", "100");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    @Override
    public void run() {
        //从消息的初始偏移量开始读取消息
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(Thread.currentThread().getName() + "---" + record.topic() + "---" + record.partition() + "---" + record.offset() + "---" + record.key() + "---" + record.value());
                //消费完消息手动提交
                kafkaConsumer.commitAsync();
            }
        }

        //订阅主题指定的分区
        /*TopicPartition[] topicPartitions = new TopicPartition[]{
                new TopicPartition("topic1", 4)
        };
        kafkaConsumer.assign(Arrays.asList(topicPartitions));

        //从消息的初始偏移量开始读取消息
        kafkaConsumer.seekToBeginning(Arrays.asList(topicPartitions));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("currentThread = %s,topic = %s, partition = %d, offset = %d,key = %s, value = %s \n", Thread.currentThread().getName(), record.topic(), record.partition(), record.offset(), record.key(), record.value());
            }
        }*/

        /*System.out.println(Thread.currentThread().getName()+"当前线程执行了...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
