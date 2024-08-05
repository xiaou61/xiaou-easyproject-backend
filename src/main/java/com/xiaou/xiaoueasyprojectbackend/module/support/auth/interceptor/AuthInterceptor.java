package com.xiaou.xiaoueasyprojectbackend.module.support.auth.interceptor;


import cn.hutool.core.util.StrUtil;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.constants.RedisConst;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.UserSessionInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.http.RequestHolder;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.utils.RedisCache;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 用户验证拦截器
 * @Author: sxgan
 * @Date: 2024/3/1 15:16
 * @Version: 1.0
 **/
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Autowired
    RedisCache redisUtil;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            return false;
        }
        // 拦截器在bean初始化前执行的，这时候redisUtil是null，需要通过下面这个方式去获取
        if (redisUtil == null) {
            WebApplicationContext wac = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(request.getServletContext());
            redisUtil = wac.getBean(RedisCache.class);
        }
        // 鉴权Token
        UserSessionInfo user = redisUtil.get(RedisConst.LOGIN_TOKEN_PREFIX + token, UserSessionInfo.class);
        log.info("current user = {}", user);
        if (StrUtil.isBlankIfStr(user)) {
            log.info("user is blank");
            return false;
        }
        RequestHolder.add(user);
        // 重置登陆时间
        redisUtil.set(RedisConst.LOGIN_TOKEN_PREFIX + token, user, RedisConst.LOGIN_TIME_1, TimeUnit.DAYS);
        return true;
    }
    
   
    
}