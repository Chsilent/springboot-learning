package com.walker.datasource;

/**
 * 数据源保存到本地线程
 *
 * @author Walker
 * @date 2020/8/20 4:24 下午
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 从从库中获取数据，可以从多个从库读取
     */
    public static void read() {
        local.set(DataSourceType.SLAVE.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.MASTER.getType());
    }

    /**
     * 切换到推荐系统数据库
     */
    /*public static void recommend() {
        local.set(DataSourceType.RECOMMEND.getType());
    }*/

    /**
     * 获取当前的数据库类型
     *
     * @return
     */
    public static String getCurrentType() {
        return local.get();
    }

    /**
     * 清空当前的连接
     */
    public static void clear() {
        local.remove();
    }
}
