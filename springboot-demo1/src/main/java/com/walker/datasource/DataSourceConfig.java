package com.walker.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 数据源配置类 将数据源配置到Spring容器中
 *
 * @author Walker
 * @date 2020/8/13 8:02 下午
 */
@Slf4j
@AutoConfigureAfter({DataSourceProperties.class})
@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    /**
     * 注册dataSource
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        //读取配置文件并构造目标数据源并存放到map中
        Map<Object, Object> targetDataSourceMap = new HashMap<>(16);
        Map<String, HikariDataSource> dataSourceMap = dataSourceProperties.getDataSourceMap();
        //多数据源配置为空直接退出
        if (CollectionUtils.isEmpty(dataSourceMap)) {
            log.error("data source map is empty, project start error!");
            System.exit(0);
        }
        AtomicReference<HikariDataSource> defaultDataSource = new AtomicReference<>();
        AtomicInteger slaveCount = new AtomicInteger();
        dataSourceMap.forEach((key, value) -> {
            targetDataSourceMap.put(value.getPoolName(), value);
            if (value.getPoolName().equals(dataSourceProperties.getDefaultChoice())) {
                defaultDataSource.set(value);
            }
            if (key.contains(DataSourceType.SLAVE.getType())) {
                slaveCount.getAndIncrement();
            }
        });

        DynamicDataSource dynamicDataSource = new DynamicDataSource(slaveCount.get());
        dynamicDataSource.setTargetDataSources(targetDataSourceMap);
        if (null != defaultDataSource.get()) {
            dynamicDataSource.setDefaultTargetDataSource(defaultDataSource.get());
        } else {
            log.error("Can't find default data source, project will be exit!");
            System.exit(0);
        }
        return dynamicDataSource;
    }

    /**
     * 事物管理
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
