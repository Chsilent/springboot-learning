package com.walker.springbootswagger.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Walker
 * @date 2021/1/8 2:21 下午
 */
@Accessors(chain = true)
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2647818944835428796L;

    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "页码")
    private String pageNum;
    @ApiModelProperty(value = "数量")
    private String pageSize;
}
