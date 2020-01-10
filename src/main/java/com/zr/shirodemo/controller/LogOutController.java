package com.zr.shirodemo.controller;

import com.zr.shirodemo.domain.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description:
 *
 * @author zhangr
 * 2020/1/9 14:10
 */
@Api("logout")
@RestController
public class LogOutController {
//    @ApiOperation(value = "/logout")
//    @GetMapping("/logout")
//    public JsonData<Object> needLogin() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return new JsonData<>(0, null, "logout");
//    }

}
