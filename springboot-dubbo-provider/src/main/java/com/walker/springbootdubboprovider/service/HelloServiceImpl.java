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
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            log.error("********error***********:", e);
        }*/
        return "hello  ".concat(name);
    }
}
