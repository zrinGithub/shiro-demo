package com.zr.shirodemo.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.AssertTrue;

/**
 * Description:
 * JdbcRealm测试
 *
 * @author zhangr
 * 2020/1/8 9:23
 */
public class JdbcRealmDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcRealmDemo.class);

    @Test
    public void testAuthentication() {
        //创建SecurityManager 工厂，通过配置文件 ini 创建
        Factory factory = new IniSecurityManagerFactory("classpath:jdbcrealm.ini");

        SecurityManager securityManager = (SecurityManager) factory.getInstance();

        //将securityManager设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        //用户输入的账号密码
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());
        LOGGER.info("认证结果：{}", subject.isAuthenticated());
        LOGGER.info("是否有对应的user角色：{}", subject.hasRole("user"));
        LOGGER.info("isPermitted('video:find'){}", subject.isPermitted("video:find"));
    }

}
