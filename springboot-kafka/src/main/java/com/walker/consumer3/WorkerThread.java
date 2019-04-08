package com.walker.consumer3;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体的消息逻辑处理类
 *
 * @author Walker
 * @date 2019/3/14 下午5:28
 */
public class WorkerThread implements Runnable {

    private ConsumerRecords<String, String> records;

    private final Map<TopicPartition, OffsetAndMetadata> offsets;

    /**
     * 构造
     *
     * @param record
     */
    public WorkerThread(ConsumerRecords record, Map<TopicPartition, OffsetAndMetadata> offsets) {
        this.records = record;
        this.offsets = offsets;
    }

    @Override
    public void run() {

        for (TopicPartition partition : records.partitions()) {
            List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
            for (ConsumerRecord<String, String> record : partitionRecords) {
                // 插入消息处理逻辑，这里打印消息
                System.out.println(String.format("topic=%s, partition=%d, offset=%d",
                        record.topic(), record.partition(), record.offset()));
            }
            // 上报消息偏移量
            long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
            //同步加锁
            synchronized (offsets) {
                if (!offsets.containsKey(partition)) {

                    offsets.put(partition, new OffsetAndMetadata(lastOffset + 1));
                } else {
                    long currentOffsets = offsets.get(partition).offset();
                    if (currentOffsets <= lastOffset + 1) {
                        offsets.put(partition, new OffsetAndMetadata(lastOffset + 1));
                    }
                }
            }
        }
    }
}
