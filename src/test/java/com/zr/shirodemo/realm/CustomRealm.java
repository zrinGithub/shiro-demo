package com.zr.shirodemo.realm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * 自定义Realm
 *
 * @author zhangr
 * 2020/1/8 10:25
 */
public class CustomRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);

    //认证信息
    private final Map<String, String> userInfoMap = new HashMap<>();

    {
        userInfoMap.put("jack", "123");
        userInfoMap.put("tom", "456");
    }

    //角色信息
    private final Map<String, Set<String>> roleMap = new HashMap<>();

    {
        Set<String> jack = new HashSet<>();
        Set<String> tom = new HashSet<>();

        //两个角色
        jack.add("role1");
        jack.add("role2");

        tom.add("role1");

        roleMap.put("jack", jack);
        roleMap.put("tom", tom);
    }

    //权限信息
    private final Map<String, Set<String>> permissionMap = new HashMap<>();

    {
        Set<String> role1 = new HashSet<>();
        Set<String> role2 = new HashSet<>();

        role1.add("video:find");
        role1.add("video:buy");

        role2.add("video:add");
        role2.add("video:delete");

        permissionMap.put("role1", role1);
        permissionMap.put("role2", role2);
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOGGER.info("获取权限信息：doGetAuthorizationInfo");

        //获取用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();

        //获取角色信息
        Set<String> roles = getRolesByName(userName);

        //获取权限信息
        Set<String> permissions = new HashSet<>();
        for (String role : roles) {
            Set<String> permission = getPermissionsByRole(role);
            permissions.addAll(permission);
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info("获取认证信息：doGetAuthenticationInfo");

        //获取用户名
        String userName = (String) authenticationToken.getPrincipal();

        //获取密码
        String pwd = getPwdByUserName(userName);

        if (StringUtils.isBlank(pwd)) {
            return null;
        }

        //返回认证信息，第三个参数是 CachingRealm 提供的getName
        return new SimpleAuthenticationInfo(userName, pwd, this.getName());
    }

    /**
     * 获取用户角色集合
     */
    private Set<String> getRolesByName(String userName) {
        return roleMap.get(userName);

    }

    /**
     * 获取权限集合
     */
    private Set<String> getPermissionsByRole(String role) {
        return permissionMap.get(role);
    }


    /**
     * 获取验证
     */
    private String getPwdByUserName(String userName) {
        return userInfoMap.get(userName);
    }
}
