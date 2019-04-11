package com.walker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置中心测试
 * @RefreshScope 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
 * 客户端使用Post方式，执行http://localhost:{port}/actuator/refresh请求时，会更新此类下的变量值
 * 采用总线方式: Post地址有所变化:http://localhost:{port}/actuator/bus-refresh
 * @author Walker
 * @date 2019/4/11 11:07 AM
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${config-value}")
    private String configValue;

    /**
     * 获取配置中心的配置文件值
     *
     * @return
     */
    @RequestMapping("config")
    public String getConfigValue() {

        return this.configValue;
    }
}
