package com.walker.consumer2;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Walker
 * @date 2019/3/14 下午4:27
 */
public class ConsumerRunnable implements Runnable {
    /**
     * 每个线程维护私有的KafkaConsumer实例
     */
    private final KafkaConsumer<String, String> consumer;

    /**
     * @param brokerList
     * @param groupId
     * @param topic
     */
    public ConsumerRunnable(String brokerList, String groupId, String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupId);
        //本例使用手动提交位移
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        //指定消费者在读取一个没有偏移量的分区或者偏移量无效的情况下如何处理，earliest和latest分别表示最早和最近的
        //props.put("auto.offset.reset", "earliest");
        //消费分区的分配策略RangeAssignor/RoundRobinAssignor
        //props.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RangeAssignor");
        //props.put("max.poll.records", "100");

        this.consumer = new KafkaConsumer<>(props);
        // 本例使用分区副本自动分配策略
        consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        while (true) {
            // 本例使用200ms作为获取超时时间
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));
            for (ConsumerRecord<String, String> record : records) {
                // 这里面写处理消息的逻辑，本例中只是简单地打印消息
                System.out.println("ThreadName:" + Thread.currentThread().getName() + "----" + " consumed: " + record.partition() + "----" +
                        "offset: " + record.offset() + "----" + "value: " + record.value());
                consumer.commitAsync();
            }
        }
    }
}
