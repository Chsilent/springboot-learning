package com.walker.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Walker
 * @date 2020/8/13 7:44 下午
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 数据源的个数
     **/
    private final int slaveDataSourceNumber;
    /**
     * 读节点的count
     **/
    private AtomicLong readCount = new AtomicLong(1);

    /**
     * 构造
     *
     * @param slaveDataSourceNumber
     */
    public DynamicDataSource(int slaveDataSourceNumber) {
        this.slaveDataSourceNumber = slaveDataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getCurrentType();
        //未明确指定数据库类型时,使用默认数据库
        if (null != typeKey) {
            //写库的
            if (typeKey.equals(DataSourceType.MASTER.getType())) {
                return DataSourceType.MASTER.getType();
            }
            if (typeKey.equals(DataSourceType.SLAVE.getType())) {
                return DataSourceType.SLAVE.getType();
            }
            //从库 做简单的负载均衡 这里从库只有一个 不需要负载均衡
            //long number = readCount.getAndAdd(1L);
            //long lookUpKey = number % slaveDataSourceNumber;
            //return String.format("%s%s", DataSourceType.SLAVE.getType(), lookUpKey);
        }
        return null;
    }


}
