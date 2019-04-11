package com.walker.consumer;

import com.walker.common.threadUtils.ConsumerThread;
import com.walker.common.utils.ResultInfo;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Walker
 * @date 2019/3/12 下午2:51
 */
@RestController
@RequestMapping("kafka")
public class ConsumerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.kafka.bootstrap-servers}")
    private String broker;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    /**
     * 通过多线程消费消息
     * @return
     */
    @RequestMapping("consumer")
    public Object consumer() {
        ResultInfo resultInfo = new ResultInfo();


        int size = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            ConsumerThread consumerThread = new ConsumerThread(broker, groupId, "topic3");
            executorService.submit(consumerThread);
        }
        return resultInfo;
    }
}
