package com.walker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Walker
 * @date 2019/4/8 6:52 PM
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String helloService(String name) {
        return "my first services name is" + name;
    }
}
