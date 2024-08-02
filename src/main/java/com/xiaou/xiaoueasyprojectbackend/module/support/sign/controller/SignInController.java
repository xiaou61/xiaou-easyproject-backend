package com.xiaou.xiaoueasyprojectbackend.module.support.sign.controller;

import com.xiaou.xiaoueasyprojectbackend.common.utils.ResultUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.BaseResponse;
import com.xiaou.xiaoueasyprojectbackend.module.support.sign.service.SignInService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/signin")
public class SignInController {


    @Resource
    private SignInService signInService;
    @GetMapping("/signin/count/today")
    public BaseResponse countSignInToday() {
        long count = signInService.countSignInToday();
        return ResultUtils.success("Total sign-ins today: " + count);
    }
    @GetMapping("/signin/count/week")
    public BaseResponse countSignInThisWeek() {
        long count = signInService.countSignInThisWeek();
        return ResultUtils.success("Total sign-ins this week: " + count);
    }
    @GetMapping("/signin/count/month")
    public BaseResponse countUserSignInThisMonth(@RequestParam long userId) {
        long count = signInService.countUserSignInThisMonth(userId);
        return ResultUtils.success("Total sign-ins this month for user " + userId + ": " + count);
    }
    @PostMapping("/signin")
    public BaseResponse signIn(@RequestParam("userId") Long userId) {
        // 调用签到服务来处理签到
        signInService.signIn(userId);
        return ResultUtils.success("User " + userId + " signed in successfully!");
    }
}