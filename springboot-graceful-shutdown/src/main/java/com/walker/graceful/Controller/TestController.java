package com.walker.graceful.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Walker
 * @date 2020/8/14 5:39 下午
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @RequestMapping(value = "hello")
    public Object helloWorld() throws InterruptedException {
        // 模拟线程执行业务耗时操作
        TimeUnit.SECONDS.sleep(20);
        log.info("==============procss finished==============");
        return "hello world";
    }

    @RequestMapping(value = "walker")
    public Object helloWalker() {
        return "hello walker";
    }
}
