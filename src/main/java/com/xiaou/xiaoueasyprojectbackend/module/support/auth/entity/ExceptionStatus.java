package com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 响应状态枚举
 * @Author: sxgan
 * @Date: 2024/3/1 15:05
 * @Version: 1.0
 **/
@Getter
@AllArgsConstructor
public enum ExceptionStatus {
    // 账户异常
    EXCEPTION_STATUS_700(700, "账号未登录"),
    EXCEPTION_STATUS_701(701, "账号未注册"),
    EXCEPTION_STATUS_702(702, "注册失败"),
    EXCEPTION_STATUS_703(703, "账号密码错误"),
    EXCEPTION_STATUS_704(704, "注册邮箱、密码、验证码不能为空"),
    EXCEPTION_STATUS_705(705, "你填写的验证码有误"),
    EXCEPTION_STATUS_706(706, "该邮箱已经注册，请前往登陆"),
    EXCEPTION_STATUS_707(707, "更新失败"),
    
    
    // 邮件发送异常
    EXCEPTION_STATUS_711(711, "邮件为空"),
    EXCEPTION_STATUS_712(712, "邮件发送失败，系统异常"),
    
    // 请求异常
    EXCEPTION_STATUS_900(900, "请求参数异常，参数为空"),
    // 业务错误
    EXCEPTION_STATUS_999(999, "业务错误");
    
    public static final List<ExceptionStatus> EXCEPTION_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(EXCEPTION_STATUS_700, EXCEPTION_STATUS_701, EXCEPTION_STATUS_702, EXCEPTION_STATUS_703,
                    EXCEPTION_STATUS_704, EXCEPTION_STATUS_705, EXCEPTION_STATUS_706, EXCEPTION_STATUS_707,
                    EXCEPTION_STATUS_711, EXCEPTION_STATUS_712, EXCEPTION_STATUS_900, EXCEPTION_STATUS_999)
    );
    
    /* exception code*/
    private final Integer exceptionCode;
    
    /* exception msg.*/
    private final String exceptionMsg;
    
}
