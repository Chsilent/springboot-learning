package com.walker.controller;

import com.walker.remote.HelloRemoteHystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试：http://localhost:9001/hello?name=chennz
 * @author Walker
 * @date 2019/4/8 7:07 PM
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloRemoteHystrix helloRemoteHystrix;


    /**
     * 远程调用服务，服务不可用时，启用熔断机制
     *
     * @param name
     * @return
     */
    @RequestMapping("hello")
    public String testHystrix(@RequestParam String name) {
        return helloRemoteHystrix.hello(name);
    }
}
