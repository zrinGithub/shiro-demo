package com.zr.shirodemo.dao;

import com.zr.shirodemo.domain.Role;
import com.zr.shirodemo.dto.RoleDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Description:
 * 角色mapper
 *
 * @author zhangr
 * 2020/1/9 10:26
 */
public interface RoleMapper {
    @Select("select ur.role_id as id, " +
            "r.name as name, " +
            "r.description as description " +
            " from  user_role ur left join role r on ur.role_id = r.id " +
            "where  ur.user_id = #{userId}")
    @Results(
            value = {
                    @Result(id=true, property = "id",column = "id"),
                    @Result(property = "name",column = "name"),
                    @Result(property = "description",column = "description"),
                    @Result(property = "permissionList",column = "id",
                            many = @Many(select = "com.zr.shirodemo.dao.PermissionMapper.findPermissionListByRoleId", fetchType = FetchType.DEFAULT)
                    )
            }
    )
    List<RoleDto> findRoleListByUserId(@Param("userId")int userId);
}
