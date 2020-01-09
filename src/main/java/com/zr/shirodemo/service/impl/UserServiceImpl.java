package com.zr.shirodemo.service.impl;

import com.zr.shirodemo.dao.RoleMapper;
import com.zr.shirodemo.dao.UserMapper;
import com.zr.shirodemo.domain.Role;
import com.zr.shirodemo.domain.User;
import com.zr.shirodemo.dto.RoleDto;
import com.zr.shirodemo.dto.UserDto;
import com.zr.shirodemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/9 11:47
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDto findAllUserInfoByUsername(String username) {
        UserDto user = userMapper.findByUserName(username);

        //用户的角色集合
        List<RoleDto> roleList = roleMapper.findRoleListByUserId(user.getId());


        user.setRoleList(roleList);

        return user;
    }

    @Override
    public User findSimpleUserInfoById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public UserDto findSimpleUserInfoByUsername(String username) {
        return userMapper.findByUserName(username);
    }
}
