package com.xiaou.xiaoueasyprojectbackend.module.support.auth.service;

import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.ResponseResult;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.UserSessionInfo;

import java.util.Map;

public interface IAuthService {
    /**
     * 通过邮箱验证用户
     *
     * @param userSessionInfo 当前登录用户
     * @return 存在返回用户
     */
    ResponseResult<Map<String, String>> userAuthByEmail(UserSessionInfo userSessionInfo);
    
    /**
     * 通过邮箱注册用户
     *
     * @param userSessionInfo 当前登录用户
     * @return 提示消息
     */
    ResponseResult<Map<String, String>> signupUserByEmail(UserSessionInfo userSessionInfo);
}
