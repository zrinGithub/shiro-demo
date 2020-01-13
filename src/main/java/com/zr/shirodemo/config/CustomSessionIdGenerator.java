package com.zr.shirodemo.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * Description:
 * 自定义sessionIdGenerator
 *
 * @author zhangr
 * 2020/1/13 10:50
 * }
 */
public class CustomSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return "zr-shiro-demo" + UUID.randomUUID().toString().replaceAll("-", "");
    }
}
