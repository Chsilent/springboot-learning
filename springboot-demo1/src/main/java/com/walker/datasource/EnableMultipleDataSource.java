package com.walker.datasource;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动多数据源注解
 *
 * @author walker
 * @version 1.0
 * @className EnableMultipleDataSource
 * @date 2019-08-12 11:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({DataSourceProperties.class, DataSourceAspect.class, DataSourceConfig.class})
public @interface EnableMultipleDataSource {
}
