package com.walker.consumer2;

/**
 * 多线程消费-基于消费组，多个消费者消费不同的分区
 * 推荐用法
 * @author Walker
 * @date 2019/3/14 下午4:32
 */
public class ConsumerMain {

    public static void main(String[] args) {

        String brokerList = "127.0.0.1:9092";
        String groupId = "walkerTest2";
        String topic = "topic1";
        int consumerNum = 5;

        ConsumerGroup consumerGroup = new ConsumerGroup(consumerNum, groupId, topic, brokerList);
        consumerGroup.execute();
    }
}
