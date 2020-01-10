package com.zr.shirodemo.md5;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;

/**
 * Description:
 *
 * @author zhangr
 * 2020/1/10 15:23
 * }
 */
public class MD5Test {
    @Test
    public void testMD5(){
        //加密算法
        String hashName = "md5";
        //密码明文
        String pwd = "123456";
        //加密函数，使用shiro自带的
        Object result = new SimpleHash(hashName, pwd, null, 2);
        System.out.println(result);
    }
}
