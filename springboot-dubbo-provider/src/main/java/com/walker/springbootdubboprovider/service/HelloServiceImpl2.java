package com.walker.springbootdubboprovider.service;

import com.walker.demo.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author Walker
 * @date 2020/10/13 11:10 上午
 */
@Slf4j
@DubboService(version = "1.0.0", group = "chennz")
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String sayHello(String name) {
        log.info("async provider received: {}", name);
        return "hello2".concat(name);
    }
}
