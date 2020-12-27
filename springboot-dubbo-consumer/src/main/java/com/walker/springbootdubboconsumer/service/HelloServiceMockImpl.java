package com.walker.springbootdubboconsumer.service;

import com.walker.demo.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Walker
 * @date 2020/12/15 3:56 下午
 */
@Slf4j
@Service
public class HelloServiceMockImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        log.error("mock method start.........,name is:" + name);
        return "mock";
    }
}
