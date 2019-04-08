package com.walker.consumer2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 消费组
 *
 * @author Walker
 * @date 2019/3/14 下午4:31
 */
public class ConsumerGroup {

    private List<ConsumerRunnable> consumers;

    final int size = 5;
    final long awaitTime = 5 * 1000;

    /**
     * 创建多个消费者,每个消费者对应一个线程
     *
     * @param consumerNum
     * @param groupId
     * @param topic
     * @param brokerList
     */
    public ConsumerGroup(int consumerNum, String groupId, String topic, String brokerList) {
        consumers = new ArrayList<>(consumerNum);
        for (int i = 0; i < consumerNum; ++i) {
            ConsumerRunnable consumerThread = new ConsumerRunnable(brokerList, groupId, topic);
            consumers.add(consumerThread);
        }
    }

    /**
     * 消费者消费消息
     */
    public void execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (ConsumerRunnable consumer : consumers) {
            executorService.submit(consumer);
        }

        try {
            //阻止新来的任务提交，对已经提交了的任务不会产生任何影响。当已经提交的任务执行完后，会将那些闲置的线程进行中断
            executorService.shutdown();


            // awaitTermination会一直等待，直到线程池状态为TERMINATED或者，等待的时间到达了指定的时间
            // 阻止新来的任务提交，同时会中断当前正在运行的线程，另外它还将工作队列中的任务给移除，并将这些任务添加到列表中进行返回
            if (!executorService.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            System.out.println("awaitTermination interrupted: " + e);
            executorService.shutdownNow();
        }
    }
}
