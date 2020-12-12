package com.walker.springbootdubboconsumer.bean.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Walker
 * @date 2020/11/25 8:18 下午
 */
@Data
@Accessors(chain = true)
public class ResultInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求成功的响应码及相应描述
     */
    private static final Integer REQUEST_SUCCESS_STATUS = 100000;

    private static final String REQUEST_SUCCESS_MSG = "ok";

    /**
     * 业务状态码
     */
    private Integer status;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public ResultInfo() {
        status = REQUEST_SUCCESS_STATUS;
        msg = REQUEST_SUCCESS_MSG;
    }

    public ResultInfo(Integer code, String msg) {
        this.status = code;
        this.msg = msg;
    }

    public ResultInfo(Integer code, String msg, Object data) {
        this.status = code;
        this.msg = msg;
        this.data = data;
    }
}
