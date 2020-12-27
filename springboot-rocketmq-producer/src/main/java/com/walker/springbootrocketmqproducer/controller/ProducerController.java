package com.walker.springbootrocketmqproducer.controller;

import com.walker.springbootrocketmqproducer.common.CommonConstant;
import com.walker.springbootrocketmqproducer.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author walker
 * @Date 2020/12/27 10:52
 */
@RestController
@Slf4j
@RequestMapping("rocket/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    /**
     * send msg to rocketmq
     *
     * @param msg
     * @return
     */
    @RequestMapping("send")
    public Object send(String msg) {
        producerService.send(msg);
        return CommonConstant.RESULT_OK;
    }
}
