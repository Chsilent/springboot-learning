package com.walker.springbootrocketmqproducer.service;

import com.walker.springbootrocketmqproducer.rocket.service.RocketMqProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author walker
 * @Date 2020/12/27 10:53
 */
@Service
public class ProducerService {

    @Autowired
    private RocketMqProducerService rocketMqProducerService;

    /**
     * send
     *
     * @param msg
     */
    public void send(String msg) {
        rocketMqProducerService.sendMsg(msg);
    }
}
