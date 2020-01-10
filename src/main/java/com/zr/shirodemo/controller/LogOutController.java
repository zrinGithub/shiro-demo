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
 * logout是shiro自带的，不用开发
 * @author zhangr
 * 2020/1/9 14:10
 */
@Api("logout")
@RestController
public class LogOutController {
}
