package com.walker.remote;

import org.springframework.stereotype.Component;

/**
 * 服务熔断回调方法：在服务熔断时执行改方法
 * @author Walker
 * @date 2019/4/9 10:28 AM
 */
@Component
public class HelloRemoteHystrixImpl implements HelloRemoteHystrix {

    @Override
    public String hello(String name) {
        return "the message " + name + " encountered problem!";
    }

}
