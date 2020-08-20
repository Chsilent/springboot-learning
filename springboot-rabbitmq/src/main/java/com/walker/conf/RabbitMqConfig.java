package com.walker.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit mq config
 *
 * @author Walker
 * @date 2019/7/25 11:21 AM
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 定义队列
     *
     * @return
     */
    @Bean(name = "walker_queue")
    public Queue queue() {
        return new Queue(RabbitMqEnum.TEST.getQueueName());
    }

    /**
     * 定义交换机
     *
     * @return
     */
    @Bean(name = "walker_exchange")
    public DirectExchange exchange() {
        return new DirectExchange(RabbitMqEnum.TEST.getExchangeName(), true, false);
    }

    /**
     * 队列与交换机通过route key binding
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean(name = "walker_binding")
    public Binding bindingExchange(@Qualifier("walker_queue") Queue queue, @Qualifier("walker_exchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqEnum.TEST.getRouteKey());
    }

    /**
     * 消息序列化
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
