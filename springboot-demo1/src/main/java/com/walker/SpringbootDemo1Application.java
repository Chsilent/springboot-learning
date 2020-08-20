package com.walker;

import com.walker.datasource.EnableMultipleDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan(basePackages = {"com.walker.mapper.*"})
@EnableMultipleDataSource
@SpringBootApplication(scanBasePackages = {"com.walker.*"}, exclude = DataSourceAutoConfiguration.class)
/*@SpringBootApplication(scanBasePackages = {"com.walker.*"})*/
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class SpringbootDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo1Application.class, args);
    }

}

