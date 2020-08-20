package com.walker.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 接收rabbitmq的消息
 *
 * @author Walker
 * @date 2018/11/4 下午10:31
 */
@Component
public class Receiver {

    @RabbitHandler
    @RabbitListener(queues = "walker_queue")
    public void receive(Message message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("body:" + body);
        System.out.println("receive msg:" + message);
    }

}
