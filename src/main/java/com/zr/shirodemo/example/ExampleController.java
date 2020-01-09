package com.zr.shirodemo.example;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * 测试shiro注解的权限控制
 *
 * @author zhangr
 * 2020/1/8 15:27
 */
@RestController
@RequestMapping("/api/admin/user")
public class ExampleController {
    //两个权限同时成立才能进行操作
//    @RequiresPermissions(value = {"user:add", "user:del"}, logical = Logical.AND)
    @RequiresRoles(value = {"admin", "editor"}, logical = Logical.AND)
    @GetMapping("/user/list")
    public String listUser() {
        return "pass";
    }
}
