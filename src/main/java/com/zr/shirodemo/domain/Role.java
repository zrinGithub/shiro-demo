package com.zr.shirodemo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * 角色信息
 *
 * @author zhangr
 * 2020/1/9 10:21
 */
@Data
@ApiModel("角色信息")
public class Role {
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
