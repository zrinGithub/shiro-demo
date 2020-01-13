package com.zr.shirodemo.dto;

import com.zr.shirodemo.domain.Permission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * 角色信息
 *
 * @author zhangr
 * 2020/1/9 10:21
 */
@Data
@ApiModel("角色信息")
public class RoleDto implements Serializable {
    private static final long serialVersionUID = 5165900195989846796L;

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("权限列表")
    private List<PermissionDto> permissionList;
}
