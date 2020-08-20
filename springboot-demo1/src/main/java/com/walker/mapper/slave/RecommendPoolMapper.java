package com.walker.mapper.slave;

import com.walker.entity.RecommendPool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendPoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecommendPool record);

    int insertSelective(RecommendPool record);

    RecommendPool selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecommendPool record);

    int updateByPrimaryKey(RecommendPool record);

    /**
     * query
     *
     * @param recommendPool
     * @return
     */
    List<RecommendPool> query(RecommendPool recommendPool);

    /**
     * query all
     *
     * @return
     */
    List<RecommendPool> queryAll();

    /**
     * find name by id
     *
     * @param id
     * @return
     */
    String findNameById(Integer id);
}