package com.zr.shirodemo.dao;

import com.zr.shirodemo.dto.PermissionDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description:
 * 权限mapper
 *
 * @author zhangr
 * 2020/1/9 10:58
 */
public interface PermissionMapper {
    @Select("select p.id as id, p.name as name, p.url as url from  role_permission rp " +
            "left join permission p on rp.permission_id=p.id " +
            "where  rp.role_id= #{roleId} ")
    List<PermissionDto> findPermissionListByRoleId(@Param("roleId") int roleId);
}
