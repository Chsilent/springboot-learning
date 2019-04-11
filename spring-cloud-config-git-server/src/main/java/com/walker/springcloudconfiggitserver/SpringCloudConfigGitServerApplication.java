package com.walker.springcloudconfiggitserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @EnableConfigServer :激活对配置中心的支持
 * @EnableDiscoveryClient :激活对注册中心的支持，将服务注册到eureka中
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudConfigGitServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigGitServerApplication.class, args);
    }

}
