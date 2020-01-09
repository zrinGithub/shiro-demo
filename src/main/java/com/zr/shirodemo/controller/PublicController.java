package com.zr.shirodemo.controller;

import com.zr.shirodemo.dto.UserDto;
import com.zr.shirodemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/9 14:10
 */
@Api("PublicController")
@RestController
@RequestMapping("pub")
public class PublicController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "find_user_info")
    @GetMapping("find_user_info")
    public UserDto findUserInfo(@RequestParam("userName")String userName){
        return userService.findAllUserInfoByUsername(userName);
    }
}
