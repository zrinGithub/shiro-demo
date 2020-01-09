package com.zr.shirodemo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 * 用户信息
 *
 * @author zhangr
 * 2020/1/9 10:15
 */
@Data
@ApiModel("用户信息")
public class User {
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("盐")
    private String salt;
}
