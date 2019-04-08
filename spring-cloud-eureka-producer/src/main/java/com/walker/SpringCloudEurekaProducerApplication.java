package com.walker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableDiscoveryClient 项目就具有了服务注册的功能。启动工程后，就可以在注册中心的页面看到SPRING-CLOUD-PRODUCER服务
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudEurekaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaProducerApplication.class, args);
    }

}
