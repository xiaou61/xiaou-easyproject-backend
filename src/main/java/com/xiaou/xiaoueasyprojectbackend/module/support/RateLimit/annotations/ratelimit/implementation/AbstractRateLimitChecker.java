package com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.implementation;


import com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.RateLimit;


public abstract class AbstractRateLimitChecker {

    /**
     * 检查是否超出限流
     *
     * @param rateLimiter RateLimit
     */
    public abstract void check(RateLimit rateLimiter);

}
