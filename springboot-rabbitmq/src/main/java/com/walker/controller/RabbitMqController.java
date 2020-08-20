package com.walker.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Walker
 * @date 2019/7/25 7:59 PM
 */
@RestController
@RequestMapping("api/rabbit")
public class RabbitMqController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("send")
    public Object send(String message) {
        Map<String, String> map = new HashMap<>();
        map.put("walker", "chennz");
        map.put("age", "22");
        amqpTemplate.convertAndSend("walker_exchange", "walker_route_key", map);
        //amqpTemplate.convertAndSend("walker_exchange", "walker_route_key", message);
        return "success";
    }

    public Object send2(String message) {
        amqpTemplate.convertAndSend("walker_queue", message);
        return "success";
    }
}
