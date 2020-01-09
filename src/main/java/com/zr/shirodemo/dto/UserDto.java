package com.zr.shirodemo.dto;

import com.zr.shirodemo.domain.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/9 11:07
 */
@ApiModel("用户角色权限信息")
@Data
public class UserDto {
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

    @ApiModelProperty("角色列表")
    private List<RoleDto> roleList;
}
