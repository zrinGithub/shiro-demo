package com.zr.shirodemo.realm;

import com.zr.shirodemo.base.QuickStart;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * ini 文件配置realm
 *
 * @author zhangr
 * 2020/1/7 18:55
 */
public class IniRealmDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuickStart.class);

    @Test
    public void testAuthentication() {
        //创建SecurityManager 工厂，通过配置文件 ini 创建
        Factory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = (SecurityManager) factory.getInstance();

        //将securityManager设置到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        //用户输入的账号密码
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "456");

        subject.login(token);

        LOGGER.info("认证结果：{}", subject.isAuthenticated());
        LOGGER.info("是否有对应的user角色：{}", subject.hasRole("user"));
        LOGGER.info("getPrincipal()：{}", subject.getPrincipal());
        subject.checkRole("user");
        subject.checkPermission("video:find");
        LOGGER.info("isPermitted('video:find'){}", subject.isPermitted("video:find"));
        subject.logout();
        LOGGER.info("logout后认证结果：{}", subject.isAuthenticated());
    }
}
