package com.zr.shirodemo.service;

import com.zr.shirodemo.domain.User;
import com.zr.shirodemo.dto.UserDto;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/9 11:45
 */
public interface UserService {

    /**
     * 获取全部用户信息
     */
    UserDto findAllUserInfoByUsername(String username);

    /**
     * 获取用户基本信息
     */
    User findSimpleUserInfoById(int userId);

    /**
     * 根据用户名查找用户信息
     */
    UserDto findSimpleUserInfoByUsername(String username);

}
