package com.walker.springbootgrpcconsumer.controller;

import com.walker.springbootgrpcconsumer.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Walker
 * @date 2021/7/24 15:34
 */
@Slf4j
@RestController
@RequestMapping("api/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("sayHello")
    public Object hello(String name, String sex) {
        return helloService.sayHello(name, sex);
    }
}
