package com.walker.controller;

import com.walker.remote.HelloRemote;
import com.walker.remote.HelloRemoteHystrix;
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

    @Autowired
    HelloRemoteHystrix helloRemoteHystrix;

    /**
     * 远程调用服务
     * @param name
     * @return
     */
    /*@RequestMapping("hello")
    public String getHelloService(@RequestParam String name) {
        return helloRemoteImpl.hello(name);
    }*/

    /**
     * 远程调用服务，服务不可用时，启用熔断机制
     * @param name
     * @return
     */
    @RequestMapping("hello")
    public String testHystrix(@RequestParam String name) {
        return helloRemoteHystrix.hello(name);
    }
}
