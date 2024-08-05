package com.xiaou.xiaoueasyprojectbackend.module.support.auth.service;

public interface IMailSendService {


    /**
     * 发送验证码到指定邮箱
     *
     * @param email 邮箱
     * @return
     */
    String sendVerifyCodeToSpecifiedEmail(String email);
}