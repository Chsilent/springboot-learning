package com.walker.controller;

import com.walker.remote.HelloRemote;
import com.walker.remote.HelloRemoteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Walker
 * @date 2019/4/8 7:07 PM
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloRemote helloRemote;

    @Autowired
    HelloRemoteImpl helloRemoteImpl;

    /**
     * @param name
     * @return
     */
    @RequestMapping("hello")
    public String getHelloService(@RequestParam String name) {
        String result = helloRemoteImpl.hello(name);
        return result;
    }
}
