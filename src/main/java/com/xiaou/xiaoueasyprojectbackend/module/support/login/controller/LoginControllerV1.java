package com.xiaou.xiaoueasyprojectbackend.module.support.login.controller;


import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.BaseResponse;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.dto.UserLoginRequest;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.dto.UserRegisterRequest;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.entity.User;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.vo.LoginUserVO;
import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import com.xiaou.xiaoueasyprojectbackend.common.utils.ResultUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/login")
@Tag(name = "登录/注册通用接口V1")
/**
 * @author xiaou61
 * @Date 2024/7/22 14:34
 * @Description: 登录/注册 通用接口 鱼皮
 */
public class LoginControllerV1 {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(user));
    }


}
