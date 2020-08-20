package com.walker.mapper.master;

import com.walker.entity.BucketConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BucketConfigMapper {

    /**
     * 根据dnum尾号、channel名称以及类型查询对应的分桶算法及算法版本等信息
     *
     * @param tail
     * @param channel
     * @param type
     * @return
     */
    BucketConfig query(@Param("tail") String tail, @Param("channel") String channel, @Param("type") String type);

    /**
     * 更新信息
     *
     * @param bucketConfig
     * @return
     */
    int update(BucketConfig bucketConfig);

    /**
     * 插入配置信息
     *
     * @param bucketConfig
     * @return
     */
    int insert(BucketConfig bucketConfig);

    /**
     * 删除配置信息
     *
     * @param bucketConfig
     * @return
     */
    int delete(BucketConfig bucketConfig);

    /**
     * 多条件查询分桶策略
     *
     * @param bucketConfig
     * @return
     */
    List<BucketConfig> getBucketConfigByCondition(BucketConfig bucketConfig);

    /**
     * 根据id删除批量删除分桶策略
     *
     * @param list
     * @return
     */
    int deleteBucketConfigById(@Param(value = "list") List<String> list);

    /**
     * delete by id
     *
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据主键id查询分桶信息
     *
     * @param id
     * @return
     */
    BucketConfig getBucketConfigById(Long id);

    /**
     * 批量修改分桶状态
     *
     * @param list
     * @param status
     */
    void updateBucketConfigStatus(@Param(value = "list") List<String> list, @Param(value = "status") String status);

    /**
     * 根据id修改分桶信息
     *
     * @param bucketConfig
     */
    void updateBucketConfigById(BucketConfig bucketConfig);

    /**
     * 根据id集合查询分桶配置记录
     *
     * @param list
     * @return
     */
    List<BucketConfig> getBucketConfigByIds(@Param(value = "list") List list);
}
