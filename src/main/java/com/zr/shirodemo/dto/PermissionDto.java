package com.zr.shirodemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * 权限信息
 *
 * @author zhangr
 * 2020/1/9 10:22
 */
@Data
@ApiModel("权限信息")
public class PermissionDto {
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("接口路径")
    private String url;
}
