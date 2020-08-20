package com.walker.datasource;

import java.lang.annotation.*;

/**
 * @author Walker
 * @date 2020/8/20 4:27 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface DataSourceSelector {

    /**
     * 默认的是master
     *
     * @return
     */
    DataSourceType value() default DataSourceType.MASTER;
}
