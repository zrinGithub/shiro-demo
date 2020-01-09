package com.zr.shirodemo.config;

import com.zr.shirodemo.dto.PermissionDto;
import com.zr.shirodemo.dto.RoleDto;
import com.zr.shirodemo.dto.UserDto;
import com.zr.shirodemo.service.UserService;
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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Description:
 * 自定义Realm
 *
 * @author zhangr
 * 2020/1/9 10:05
 */
public class CustomRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);

    @Resource
    private UserService userService;

    /**
     * 权限校验
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("----进行权限校验----");

        //用户名称
        String userName =(String) principals.getPrimaryPrincipal();

        UserDto user = userService.findAllUserInfoByUsername(userName);

        List<String> roleList = user.getRoleList().stream().map(RoleDto::getName).collect(toList());
        List<String> permissionList = new ArrayList<>();

        for(RoleDto roleDto:user.getRoleList()){
            //取这里面的name作为权限
            permissionList.addAll(roleDto.getPermissionList().stream().map(PermissionDto::getName).collect(toList()));
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleList);
        info.addStringPermissions(permissionList);
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOGGER.info("----进行身份认证----");

        //获取用户输入信息
        String userName = (String) token.getPrincipal();

        UserDto user = userService.findAllUserInfoByUsername(userName);

        //取密码
        String password = user.getPassword();

        if(StringUtils.isBlank(password))
            return null;

        //这里把真实的用户名和密码放到 info 中取，后面再 AuthenticatingRealm 中会进行比对
        return new SimpleAuthenticationInfo(userName,password,this.getName());
    }
}
