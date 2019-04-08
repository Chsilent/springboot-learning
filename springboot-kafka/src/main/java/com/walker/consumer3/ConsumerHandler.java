package com.walker.consumer3;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Walker
 * @date 2019/3/14 下午5:30
 */
public class ConsumerHandler {

    /**
     * 本例中使用一个consumer将消息放入后端队列
     */
    private final KafkaConsumer<String, String> consumer;

    /**
     * kafka消息元数据，包括分区信息，消息偏移量
     */
    private final Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();

    /**
     * 线程超时等待时间
     */
    final long awaitTime = 5 * 1000;

    /**
     * 线程最大空闲时间
     */
    final long keepAliveTime = 0L;

    /**
     * 阻塞队列大小
     */
    final int queueSize = 100;

    private ExecutorService executors;

    public ConsumerHandler(String brokerList, String groupId, String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupId);
        //手动提交
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        //当发生重平衡时,
        consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                consumer.commitAsync();
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                offsets.clear();
            }
        });
    }

    /**
     * 初始化线程池，然后执行消费者的工作线程
     *
     * @param workerNum
     */
    public void execute(int workerNum) {
        executors = new ThreadPoolExecutor(workerNum, workerNum, keepAliveTime, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize), new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));
                //如果不为空
                if(!records.isEmpty()){
                    executors.submit(new WorkerThread(records, offsets));
                }
                //提交偏移量
                commitOffsets();
            }
        }catch (WakeupException e){
            e.printStackTrace();
        }finally {
            shutdown();
        }
    }

    /**
     * 关闭线程池
     */
    private void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        try {
            executors.shutdown();
            if (!executors.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
                executors.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            executors.shutdownNow();
        }
    }

    /**
     * 提交偏移量
     */
    private void commitOffsets() {
        // 减少synchronized块对offsets的加锁时间，如果没有则返回一个未定义的
        Map<TopicPartition, OffsetAndMetadata> map;
        synchronized (offsets) {
            if (offsets.isEmpty()) {
                return;
            }
            map = Collections.unmodifiableMap(new HashMap<>(offsets));
            offsets.clear();
        }
        consumer.commitSync(map);
    }
}
