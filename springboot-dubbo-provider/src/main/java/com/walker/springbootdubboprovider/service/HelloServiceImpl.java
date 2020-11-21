package com.walker.springbootdubboprovider.service;

import com.walker.demo.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author Walker
 * @date 2020/10/13 11:10 上午
 */
@Service(version = "1.0.0", group = "walker")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello  ".concat(name);
    }
}
