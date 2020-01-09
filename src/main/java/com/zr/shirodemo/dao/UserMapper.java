package com.zr.shirodemo.dao;

import com.zr.shirodemo.domain.User;
import com.zr.shirodemo.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Description:
 * 用户mapper
 *
 * @author zhangr
 * 2020/1/9 10:26
 */
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{userName}")
    UserDto findByUserName(@Param("userName") String userName);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") int id);

    @Select("SELECT * FROM user WHERE username = #{userName} AND password = #{pwd}")
    UserDto findByUserNameAndPwd(@Param("userName") String userName,@Param("pwd") String pwd);
}
