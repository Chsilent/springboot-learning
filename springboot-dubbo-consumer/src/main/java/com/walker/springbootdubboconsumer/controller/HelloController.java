package com.walker.springbootdubboconsumer.controller;

import com.walker.demo.HelloService;
import com.walker.springbootdubboconsumer.bean.entity.ResultInfo;
//import com.walker.springbootdubboconsumer.service.NacosDiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Walker
 * @date 2020/10/13 11:42 上午
 */
@Slf4j
@RequestMapping("dubbo/api")
@RestController
public class HelloController {

    @DubboReference(version = "1.0.0", group = "dubbo", timeout = 3000, cluster = "failfast", mock = "com.walker.springbootdubboconsumer.service.HelloServiceMockImpl")
    private HelloService helloService;

    @DubboReference(version = "1.0.0", group = "chennz")
    private HelloService helloService2;

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
            // nacosDiscoveryService.test();
            String result = helloService.sayHello(name);
            //String result2 = helloService2.sayHello(name);
            Map<String, String> map = new HashMap<>();
            map.put("result", result);
            //map.put("result2", result2);
            resultInfo.setData(map);
        } catch (Exception e) {
            log.error("*****consumer error******:", e);
            return "8888888888888888";
        }
        return resultInfo;
    }
}
