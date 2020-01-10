package com.zr.shirodemo.controller;

import com.zr.shirodemo.domain.JsonData;
import com.zr.shirodemo.dto.UserDto;
import com.zr.shirodemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

    @Resource
    private UserService userService;

    @ApiOperation(value = "find_user_info")
    @GetMapping("find_user_info")
    public UserDto findUserInfo(@RequestParam("userName") String userName) {
        return userService.findAllUserInfoByUsername(userName);
    }

    @ApiOperation(value = "need_login")
    @GetMapping("need_login")
    public JsonData<Object> needLogin() {
        return new JsonData<>(-2, null, "请先登录");
    }

    @ApiOperation(value = "not_permit")
    @GetMapping("not_permit")
    public JsonData<Object> notPermit() {
        return new JsonData<>(-1, null, "没有权限");
    }

    @ApiOperation(value = "index")
    @GetMapping("index")
    public JsonData<List<String>> index() {
        List<String> videoList = new ArrayList<>();
        videoList.add("MySQL");
        videoList.add("Redis");
        videoList.add("React");
        return new JsonData<>(0, videoList, "查询成功");
    }

    // http://localhost:8080/pub/login?userName=jack&passWd=123
    @ApiOperation(value = "login")
    @GetMapping("login")
    public JsonData<Object> login(@RequestParam("userName") String userName
            , @RequestParam("passWd") String passWd) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> result = new HashMap<>();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, passWd);
            subject.login(token);

            result.put("session_id", subject.getSession().getId());

            return new JsonData<>(0, result, "登录成功");
        } catch (Exception e) {
            LOGGER.error("登录失败：", e);
            return new JsonData<>(-1, null, "用户名或者密码错误");
        }
    }
}
