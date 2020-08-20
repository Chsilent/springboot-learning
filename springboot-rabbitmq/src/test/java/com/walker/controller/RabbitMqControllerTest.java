package com.walker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Walker
 * @date 2019/7/26 10:25 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqControllerTest {

    @Autowired
    private RabbitMqController rabbitMqController;

    @Test
    public void test(){
        String message = "hello world";
        rabbitMqController.send(message);
    }

    @Test
    public void testQueue(){
        String message = "hello world111111 ...==========";
        rabbitMqController.send2(message);
    }
}