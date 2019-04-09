package com.walker.remote;

import org.springframework.stereotype.Component;

/**
 * 可以实现接口，并重写接口中的方法
 *
 * @author Walker
 * @date 2019/4/8 7:38 PM
 */
@Component
public class HelloRemoteImpl implements HelloRemote {

    @Override
    public String hello(String name) {
        return "the hello service result is " + name;
    }
}
