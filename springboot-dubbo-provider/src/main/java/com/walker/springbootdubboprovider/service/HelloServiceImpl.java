package com.walker.springbootdubboprovider.service;

import com.walker.demo.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Walker
 * @date 2020/10/13 11:10 上午
 */
@Slf4j
@Service(version = "1.0.0", group = "walker")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        log.info("async provider received: {}", name);
        return "hello".concat(name);
    }
}
