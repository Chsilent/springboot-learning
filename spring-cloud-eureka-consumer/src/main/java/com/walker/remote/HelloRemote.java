package com.walker.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 启用feign进行远程调用
 * @FeignClient 只可对interface声明
 * @author Walker
 * @date 2019/4/8 7:05 PM
 */
//@FeignClient(name = "spring-cloud-eureka-producer")
@Component
public interface HelloRemote {

    /**
     * name:远程服务名，即spring.application.name配置的名称
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);
}
