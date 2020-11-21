package com.walker.springbootdubboconsumer.controller;

import com.walker.demo.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Walker
 * @date 2020/10/13 11:42 上午
 */
@RequestMapping("dubbo/api")
@RestController
public class HelloController {

    @Reference(version = "1.0.0", group = "walker")
    private HelloService helloService;

    /**
     * 服务消费者调用服务提供者的服务
     *
     * @param name
     * @return
     */
    @GetMapping(value = "hello")
    public Object hello(@RequestParam("name") String name) {
        return helloService.sayHello(name);
    }
}
