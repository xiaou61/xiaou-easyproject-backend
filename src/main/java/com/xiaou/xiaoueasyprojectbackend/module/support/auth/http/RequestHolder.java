package com.xiaou.xiaoueasyprojectbackend.module.support.auth.http;

import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.UserSessionInfo;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @Description: 请求拦截，用于保存全局用户权限信息的对象
 * @Author: sxgan
 * @Date: 2024/3/1 15:16
 * @Version: 1.0
 **/
public class RequestHolder {
    private static final ThreadLocal<UserSessionInfo> userHolder = new ThreadLocal<UserSessionInfo>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(UserSessionInfo sysUser) {
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static UserSessionInfo getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}