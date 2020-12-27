package com.walker.springbootrocketmqconsumer.rocket;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 消费者配置类
 *
 * @author Walker
 * @date 2020/12/17 5:18 下午
 */
@Configuration
@Data
public class RocketMqConsumerConfig {

    @Value("${rocketMq.localCache.consumer.groupName}")
    private String groupName;
    @Value("${rocketMq.base.nameSrvAddr}")
    private String nameSrvAddr;
    @Value("${rocketMq.base.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketMq.base.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketMq.base.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;

}
