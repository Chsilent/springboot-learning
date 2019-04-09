package com.walker.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 启用feign进行远程调用,增加了fallback，在服务熔断的时候返回fallback类中的内容
 *
 * 注意：多个接口上的@FeignClient(“相同服务名”)会报错，需在配置文件中配置
 *
 * @author Walker
 * @date 2019/4/9 10:29 AM
 */
@FeignClient(name = "spring-cloud-eureka-producer", fallback = HelloRemoteHystrixImpl.class)
@Component
public interface HelloRemoteHystrix {

    @RequestMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);
}
