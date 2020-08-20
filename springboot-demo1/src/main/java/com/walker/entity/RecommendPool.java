package com.walker.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * m_recommend_pool
 *
 * @author
 */
@Data
public class RecommendPool implements Serializable {
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作人姓名
     */
    private String userName;

    private static final long serialVersionUID = 1L;

}