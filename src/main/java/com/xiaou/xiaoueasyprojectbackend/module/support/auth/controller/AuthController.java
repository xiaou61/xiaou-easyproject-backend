package com.xiaou.xiaoueasyprojectbackend.module.support.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.ExceptionStatus;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.ResponseResult;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.UserSessionInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.http.RequestHolder;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.service.impl.AuthServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.service.impl.MailSendServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.service.impl.UserServiceImplAuth;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.vo.SysUserVO;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/card/auth/")
/**
 * @author xiaou61
 * @Date 2024/8/05 18:27
 * @Description 用户认证 需要配置interceptor 以及webconfig {@link com.xiaou.xiaoueasyprojectbackend.config.WebConfig#addInterceptors(InterceptorRegistry)}
 */
public class AuthController{
    
    @Resource
    MailSendServiceImpl mailSendService;
    
    @Resource
    AuthServiceImpl authService;
    
    @Resource
    UserServiceImplAuth userService;


    /**
     * 用户登录
     * @param userSessionInfo
     * @return
     */
    @PostMapping("/signin")
    public ResponseResult<Map<String, String>> signin(@RequestBody @Validated UserSessionInfo userSessionInfo) {
        return authService.userAuthByEmail(userSessionInfo);
    }

    /**
     * 用户注册
     * @param userSessionInfo
     * @return
     */
    @PostMapping("/signup")
    public ResponseResult<Map<String, String>> signup(@RequestBody UserSessionInfo userSessionInfo) {
        return authService.signupUserByEmail(userSessionInfo);
    }

    /**
     * 发送验证码
     * @param userSessionInfo
     * @return
     */
    @PostMapping("/mailVerifyCode")
    public ResponseResult<String> sendVerifyCode(@RequestBody UserSessionInfo userSessionInfo) {
        String email = userSessionInfo.getEmail();
        if (StrUtil.isBlankIfStr(email)) {
            return ResponseResult.fail(ExceptionStatus.EXCEPTION_STATUS_900.getExceptionMsg());
        }
        String sendResult = mailSendService.sendVerifyCodeToSpecifiedEmail(email);
        if (StrUtil.isBlank(sendResult)) {
            return ResponseResult.success(sendResult);
        } else {
            return ResponseResult.fail(null, ExceptionStatus.EXCEPTION_STATUS_999.getExceptionCode(), sendResult);
        }
    }

    /**
     * 获取当前用户信息
     * @return
     */
    @GetMapping("/getSysUserInfo")
    public ResponseResult<SysUserVO> getSysUserInfo() {
        UserSessionInfo currentUser = RequestHolder.getCurrentUser();
        SysUserVO sysUserVO = userService.selectSysUserInfo(currentUser);
        if (sysUserVO == null) {
            return ResponseResult.fail(null, ExceptionStatus.EXCEPTION_STATUS_701.getExceptionCode(),
                    ExceptionStatus.EXCEPTION_STATUS_701.getExceptionMsg());
        }
        return ResponseResult.success(sysUserVO);
    }

    /**
     * 更新用户信息
     * @param sysUserVO
     * @return
     */
    @PostMapping("/updateSysUserInfo")
    public ResponseResult<String> updateSysUserInfo(@RequestBody SysUserVO sysUserVO) {
        String result = userService.updateSysUserInfo(sysUserVO);
        if (result == null) {
            return ResponseResult.fail(null, ExceptionStatus.EXCEPTION_STATUS_707.getExceptionCode(),
                    ExceptionStatus.EXCEPTION_STATUS_707.getExceptionMsg());
        }
        return ResponseResult.success(result);
    }
    
    
}