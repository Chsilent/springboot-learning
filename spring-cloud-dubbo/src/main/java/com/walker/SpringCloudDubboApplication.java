package com.walker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringCloudDubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDubboApplication.class, args);
        log.info("=====================SpringCloudDubboApplication start success=================");
    }

}
