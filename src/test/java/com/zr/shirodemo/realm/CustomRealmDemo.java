package com.zr.shirodemo.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/8 10:26
 */
public class CustomRealmDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealmDemo.class);

    private CustomRealm customRealm = new CustomRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init() {
        //构建环境
        defaultSecurityManager.setRealm(customRealm);
        //把SecurityManager 设置到当前环境中去
        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }

    @Test
    public void testAuthentication() {
        //获取操作主体
        Subject subject = SecurityUtils.getSubject();

        //用户输入的账号密码
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123");

        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());
        LOGGER.info("认证结果：{}", subject.isAuthenticated());

        subject.checkRole("role1");
        LOGGER.info("是否有对应的user角色：{}", subject.hasRole("role1"));

        LOGGER.info("是否有对应的权限：{}", subject.isPermitted("video:find"));
    }
}
