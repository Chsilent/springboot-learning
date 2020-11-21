package com.walker.service;

import com.walker.datasource.DataSourceSelector;
import com.walker.datasource.DataSourceType;
import com.walker.mapper.master.BucketConfigMapper;
import com.walker.mapper.slave.RecommendPoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author Walker
 * @date 2020/8/14 11:21 上午
 */
@Service
public class TxTestService {

    @Resource
    private BucketConfigMapper bucketConfigMapper;

    @Resource
    private RecommendPoolMapper recommendPoolMapper;

    /**
     * 删除分桶配置
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBucketConfig(Integer id) {
        bucketConfigMapper.deleteById(id);
        //同个service里，方法A开启事务，再调用开启事务的方法B，方法B的事务不生效
        //原因：Spring 事务通过AOP实现，AOP通过动态代理实现能拿到方法A的代理对象，但是调用方法B使用的是目标对象，所以B事务不生效。
        //deleteRecommendPool(3);
    }

    /**
     * 删除推荐内容池配置
     *
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @DataSourceSelector(value = DataSourceType.SLAVE)
    public void deleteRecommendPool(Integer id) {
        recommendPoolMapper.deleteByPrimaryKey(id);
    }
}
