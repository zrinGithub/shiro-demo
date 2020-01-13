package com.zr.shirodemo.config;

import com.zr.shirodemo.config.filter.CustomRolesOrAuthorizationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 * shiro 配置
 *
 * @author zhangr
 * 2020/1/9 16:33
 */
@Configuration
public class ShiroConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        LOGGER.info("执行 ShiroFilterFactoryBean 装载");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        factoryBean.setSecurityManager(securityManager);

        //如果访问的接口需要登录（没有登录），则跳转到这个路径，
        //如果没有前后端分离，则跳转页面，前后端分离就是返回路径
        factoryBean.setLoginUrl("/pub/need_login");

        //登录成功，跳转url
        factoryBean.setSuccessUrl("/");

        //登录但是没有权限
        factoryBean.setUnauthorizedUrl("/pub/not_permit");

        //保持拦截的顺序，配置参考 DefaultFilter
        Map<String, String> filterDefinitionMap = new LinkedHashMap<>();
        //退出过滤器
        filterDefinitionMap.put("/logout", "logout");
        //匿名可以访问
        filterDefinitionMap.put("/pub/**", "anon");
        //登录用户才可以访问
        filterDefinitionMap.put("/authc/**", "authc");
        //需要管理员角色才能访问
        //查看RolesAuthorizationFilter，必须要同时有这两个角色才行
        filterDefinitionMap.put("/admin/**", "roles[admin,root]");
        //有编辑权限才能访问
        filterDefinitionMap.put("/video/update", "perms[video_update]");

        //swagger对应接口放过
        filterDefinitionMap.put("/swagger-ui.html", "anon");
        filterDefinitionMap.put("/swagger-resources", "anon");
        filterDefinitionMap.put("/swagger-resources/**", "anon");
        filterDefinitionMap.put("/v2/api-docs", "anon");
        filterDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");

        //自定义filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("roleOrFilter", new CustomRolesOrAuthorizationFilter());
        factoryBean.setFilters(filterMap);
        filterDefinitionMap.put("/adminOr", "roleOrFilter[admin,root]");

        //authc: 通过认证才能访问
        //anon: 匿名访问
        filterDefinitionMap.put("/**", "authc");

        factoryBean.setFilterChainDefinitionMap(filterDefinitionMap);

        return factoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //如果不是前后端分离，不用设置 sessionManager
        securityManager.setSessionManager(sessionManager());

        //设置realm推荐放在后面，不然可能因为版本文体不生效
        securityManager.setRealm(customRealm());

        //使用自定义的redisCacheManager
        securityManager.setCacheManager(redisCacheManager());

        return securityManager;
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        //redis配置导入
        cacheManager.setRedisManager(redisManager());
        //设置过期时间，单位为秒
        cacheManager.setExpire(20);

        return cacheManager;
    }

    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        //设置散列算法：这里使用md5
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数，好比散列2次->  md5(md5(pwd))
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }

    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();

        //超时时间，默认 30分钟，会话超时；方法里面的单位是毫秒
//        customSessionManager.setGlobalSessionTimeout(20000);

        //配置session持久化
        customSessionManager.setSessionDAO(redisSessionDAO());

        return customSessionManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        //导入配置
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


}
