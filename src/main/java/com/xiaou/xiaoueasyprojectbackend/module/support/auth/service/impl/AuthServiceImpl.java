package com.xiaou.xiaoueasyprojectbackend.module.support.auth.service.impl;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.jwt.JWTUtil;

import com.google.common.collect.Maps;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.constants.RedisConst;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.ExceptionStatus;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.ResponseResult;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.SysUser;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.UserSessionInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.exception.AuthorityException;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.mapper.SysUserMapperAuth;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.query.SysUserQuery;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.service.IAuthService;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.util.PasswordEncoder;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.utils.RedisCache;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 权限校验服务
 * @Author: sxgan
 * @Date: 2024/3/1 15:14
 * @Version: 1.0
 **/
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    private final String tokenKey= "xiaoueasyprojectbackend";
    
    @Resource
    RedisCache redisUtil;
    
    @Resource
    SysUserMapperAuth userMapper;
    
    @Override
    public ResponseResult<Map<String, String>> userAuthByEmail(UserSessionInfo userSessionInfo) {
        HashMap<String, String> map = Maps.newHashMap();
        SysUserQuery userQuery = new SysUserQuery();
        userQuery.setEmail(userSessionInfo.getEmail());
        List<SysUser> userList = userMapper.selectUserByCondition(userQuery);
        if (userList.size() > 0) {
            SysUser user = userList.get(0);
            // 判断密码是否正确,加密密码与库中（user对象）的password比对是否相同
            if (PasswordEncoder.matches(user.getPassword(), userSessionInfo.getPassword())) {
                // 允许登陆，生成令牌
                // 写入用户信息
                HashMap<String, Object> tokenMap = new HashMap<>();
                tokenMap.put("email", user.getEmail());
                tokenMap.put("id", user.getUserId());
                String token = JWTUtil.createToken(tokenMap, tokenKey.getBytes());
                userSessionInfo.setId(user.getUserId());
                userSessionInfo.setUserName(user.getUserName());
                redisUtil.set(RedisConst.LOGIN_TOKEN_PREFIX + token, userSessionInfo, RedisConst.LOGIN_TIME_1,
                        TimeUnit.DAYS);
                map.put("token", token);
                return ResponseResult.success(map, 0);
            } else {
                return ResponseResult.fail(Maps.newHashMap(), ExceptionStatus.EXCEPTION_STATUS_703.getExceptionCode(),
                        ExceptionStatus.EXCEPTION_STATUS_703.getExceptionMsg());
            }
        } else {
            return ResponseResult.fail(Maps.newHashMap(), ExceptionStatus.EXCEPTION_STATUS_701.getExceptionCode(),
                    ExceptionStatus.EXCEPTION_STATUS_701.getExceptionMsg());
        }
    }
    
    @Override
    public ResponseResult<Map<String, String>> signupUserByEmail(UserSessionInfo userSessionInfo) {
        String email = userSessionInfo.getEmail();
        String password = userSessionInfo.getPassword();
        String verifyCode = userSessionInfo.getVerifyCode();
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password) || StringUtils.isBlank(verifyCode)) {
            return ResponseResult.fail(Maps.newHashMap(), ExceptionStatus.EXCEPTION_STATUS_704.getExceptionCode(),
                    ExceptionStatus.EXCEPTION_STATUS_704.getExceptionMsg());
        }
        
        // 查询当前邮箱的验证码并验证
        String captchaCacheVal = redisUtil.get(RedisConst.CARD_CAPTCHA_PREFIX + email, String.class);
        log.info("AuthServiceImpl.signupUserByEmail captchaCacheVal = {}", captchaCacheVal);
        
        
        String[] captchaCacheValArr = null;
        if (captchaCacheVal != null) {
            captchaCacheValArr = captchaCacheVal.split("_");
            if (!captchaCacheValArr[0].equals(verifyCode)) {
                return ResponseResult.fail(Maps.newHashMap(), ExceptionStatus.EXCEPTION_STATUS_705.getExceptionCode(),
                        ExceptionStatus.EXCEPTION_STATUS_705.getExceptionMsg());
            }
        } else {
            return ResponseResult.fail(Maps.newHashMap(), ExceptionStatus.EXCEPTION_STATUS_705.getExceptionCode(),
                    ExceptionStatus.EXCEPTION_STATUS_705.getExceptionMsg());
        }
        
        long sendTime = Long.parseLong(captchaCacheValArr[2]);
        long currentTime = System.currentTimeMillis();
        if (currentTime - sendTime > 60 * 5 * 1000) {
            // return "验证码已过期";
        }
        // 查询邮箱是否已经注册
        SysUserQuery userQuery = new SysUserQuery();
        userQuery.setEmail(email);
        List<SysUser> userList = userMapper.selectUserByCondition(userQuery);
        if (userList.size() > 0) {
            return ResponseResult.fail(Maps.newHashMap(), ExceptionStatus.EXCEPTION_STATUS_706.getExceptionCode(),
                    ExceptionStatus.EXCEPTION_STATUS_706.getExceptionMsg());
        }
        // 注册账号
        SysUser user = new SysUser();
        user.setUserName(RandomUtil.randomString(12).toUpperCase(Locale.ROOT));
        user.setEmail(email);
        // 加密
        
        // 生成盐
        String salt = RandomUtil.randomString(20);
        String slatPass = PasswordEncoder.encode(userSessionInfo.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(slatPass);
        int insert = userMapper.insert(user);
        if (insert == 0) {
            throw new AuthorityException(
                    ExceptionStatus.EXCEPTION_STATUS_702.getExceptionCode(),
                    ExceptionStatus.EXCEPTION_STATUS_702.getExceptionMsg());
        }
        return ResponseResult.success(Maps.newHashMap(), 0);
        
    }
}
