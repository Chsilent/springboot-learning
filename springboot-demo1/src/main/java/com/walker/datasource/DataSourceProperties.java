package com.walker.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 多数据源配置信息，从配置文件读取
 *
 * @author Walker
 * @date 2020/8/20 3:48 下午
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Configuration
public class DataSourceProperties {

    private Map<String, HikariDataSource> dataSourceMap;

    /**
     * 默认的数据源
     **/
    private String defaultChoice;
}
