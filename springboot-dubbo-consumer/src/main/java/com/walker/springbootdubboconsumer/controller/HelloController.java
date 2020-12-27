package com.walker.springbootdubboconsumer.controller;

import com.walker.demo.HelloService;
import com.walker.springbootdubboconsumer.bean.entity.ResultInfo;
import com.walker.springbootdubboconsumer.service.NacosDiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Walker
 * @date 2020/10/13 11:42 上午
 */
@Slf4j
@RequestMapping("dubbo/api")
@RestController
public class HelloController {

    @Reference(version = "1.0.0", group = "walker", timeout = 3000, cluster = "failfast", mock = "com.walker.springbootdubboconsumer.service.HelloServiceMockImpl")
    private HelloService helloService;

    @Autowired
    private NacosDiscoveryService nacosDiscoveryService;

    /**
     * 服务消费者调用服务提供者的服务
     *
     * @param name
     * @return
     */
    @GetMapping(value = "hello")
    public Object hello(@RequestParam("name") String name) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            nacosDiscoveryService.test();
            String result = helloService.sayHello(name);
            resultInfo.setData(result);
        } catch (Exception e) {
            log.error("*****consumer error******:", e);
            return "8888888888888888";
        }
        return resultInfo;
    }
}
