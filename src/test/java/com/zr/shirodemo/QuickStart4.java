package com.zr.shirodemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * 简单测试 shiro 认证流程
 *
 * @author zhangr
 * 2020/1/6 19:48
 */
public class QuickStart4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuickStart4.class);

    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init() {
        //初始化数据源：用户名、密码、角色
        accountRealm.addAccount("root", "123", "admin");
        accountRealm.addAccount("visitor", "321", "user");

        //构建环境
        defaultSecurityManager.setRealm(accountRealm);
    }

    @Test
    public void testAuthentication() {
        //设置环境的securityManager
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //设置操作的主体
        Subject subject = SecurityUtils.getSubject();

        //用户输入的账号密码，错误抛出 UnknownAccountException
        UsernamePasswordToken token = new UsernamePasswordToken("visitor", "321");

        /**
         * 登录操作：
         * 1.securityManager进行login操作
         * 2.securityManager.authenticate
         * 3.Authenticator.authenticate
         * 4.Realm.getAuthenticationInfo
         */
        subject.login(token);

        //API测试

        //认证结果，没有通过的话在login会直接抛出详细的异常
        LOGGER.info("认证的结果：{}", subject.isAuthenticated());

        //角色
        LOGGER.info("包含root角色：{}",subject.hasRole("root"));
        LOGGER.info("包含user角色：{}",subject.hasRole("user"));

        //没有输出，错误会直接报异常
        subject.checkRole("user");

        //退出登录
        subject.logout();

        LOGGER.info("logout后 认证的结果：{}", subject.isAuthenticated());
    }
}
