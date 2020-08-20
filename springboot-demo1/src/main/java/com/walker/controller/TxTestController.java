package com.walker.controller;

import com.walker.common.utils.ResultInfo;
import com.walker.service.TxTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试多数据源 controller
 *
 * @author Walker
 * @date 2020/8/14 11:20 上午
 */
@RestController
@RequestMapping("tx")
public class TxTestController {

    @Autowired
    private TxTestService txTestService;

    /**
     * 删除分桶
     * dataSource: master
     *
     * @param id
     * @return
     */
    @GetMapping("deleteBucketConfig")
    public Object deleteBucketConfig(Integer id) {
        ResultInfo resultInfo = new ResultInfo();
        txTestService.deleteBucketConfig(id);
        return resultInfo;
    }

    /**
     * 删除推荐池
     * dataSource: slave
     *
     * @param id
     * @return
     */
    @GetMapping("deleteRecommendPool")
    public Object deleteRecommendPool(Integer id) {
        ResultInfo resultInfo = new ResultInfo();
        txTestService.deleteRecommendPool(id);
        return resultInfo;
    }
}
