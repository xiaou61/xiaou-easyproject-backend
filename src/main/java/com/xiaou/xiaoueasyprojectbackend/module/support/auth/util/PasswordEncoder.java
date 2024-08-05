package com.xiaou.xiaoueasyprojectbackend.module.support.auth.util;

import cn.hutool.core.util.RandomUtil;

/**
 * @Description: 密码加密工具类
 * @Author: sxgan
 * @Date: 2024/4/26 12:19
 * @Version: 1.0
 **/
public class PasswordEncoder {
    
    public static String encode(String password) {
        // 生成盐
        String salt = RandomUtil.randomString(20);
        // 加密
        return encode(password, salt);
    }
    
    public static String encode(String password, String salt) {
        // 加密
        return salt + "@" + Md5Util.getMD5String(password + salt);
    }
    
    public static Boolean matches(String encodedPassword, String rawPassword) {
        if (encodedPassword == null || rawPassword == null) {
            return false;
        }
        if (!encodedPassword.contains("@")) {
            throw new RuntimeException("密码格式不正确！");
        }
        String[] arr = encodedPassword.split("@");
        // 获取盐
        String salt = arr[0];
        // 比较
        return encodedPassword.equals(encode(rawPassword, salt));
    }
}