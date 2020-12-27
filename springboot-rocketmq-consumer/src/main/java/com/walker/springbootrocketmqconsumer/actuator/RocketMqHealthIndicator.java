package com.walker.springbootrocketmqconsumer.actuator;

import com.walker.springbootrocketmqconsumer.rocket.LocalCacheMsgConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * rocketmq health indicator
 *
 * @Author walker
 * @Date 2020/12/27 11:42
 */
@Component
@Slf4j
public class RocketMqHealthIndicator implements HealthIndicator {

    @Value("${rocketMq.localCache.consumer.topicName}")
    private String topicName;

    @Autowired
    private LocalCacheMsgConsumer localCacheMsgConsumer;


    /**
     * rocketmq health check
     * by finding broker address, empty: health down,otherwise health up
     *
     * @return
     */
    @Override
    public Health health() {
        String brokerAddress = localCacheMsgConsumer.consumer.getDefaultMQPushConsumerImpl().getmQClientFactory().findBrokerAddrByTopic(topicName);
        if (StringUtils.isEmpty(brokerAddress)) {
            log.info("******find broker address is empty.******");
            return Health.down().build();
        }
        log.info("******find broker address is :{} ******", brokerAddress);
        return Health.up().build();
    }
}
