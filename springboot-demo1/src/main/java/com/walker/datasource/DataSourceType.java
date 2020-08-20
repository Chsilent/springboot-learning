package com.walker.datasource;

/**
 * 数据源类型枚举类
 *
 * @author Walker
 * @version 1.0
 * @enumName DataSourceType
 * @date 2019-08-07 16:57
 */
public enum DataSourceType {
    /**
     * master slave recommend
     */
    MASTER("master"), SLAVE("slave");

    /**
     * 数据源的类型
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    DataSourceType(String type) {
        this.type = type;
    }
}
