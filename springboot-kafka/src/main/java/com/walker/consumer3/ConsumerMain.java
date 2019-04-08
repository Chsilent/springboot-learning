package com.walker.consumer3;

/**
 * 多线程消费-基于消费者，多个worker线程
 *
 * 存在的问题：由于kafkaconsumer不是线程安全的，提交消息偏移量时，不能直接commitSync,
 *           也就是不设置自动提交 而是手动提交
 * @author Walker
 * @date 2019/3/14 下午5:32
 */
public class ConsumerMain {

    public static void main(String[] args) {

        String brokerList = "127.0.0.1:9092";
        String groupId = "walkerTest1";
        String topic = "topic1";
        int workerNum = 5;

        ConsumerHandler consumer = new ConsumerHandler(brokerList, groupId, topic);
        consumer.execute(workerNum);
        //consumer.shutdown();
    }

}
