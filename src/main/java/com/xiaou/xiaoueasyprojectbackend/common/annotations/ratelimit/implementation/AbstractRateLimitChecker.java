package com.xiaou.xiaoueasyprojectbackend.common.annotations.ratelimit.implementation;


import com.xiaou.xiaoueasyprojectbackend.common.annotations.ratelimit.RateLimit;

/**
 * @author valarchie
 */
public abstract class AbstractRateLimitChecker {

    /**
     * 检查是否超出限流
     *
     * @param rateLimiter RateLimit
     */
    public abstract void check(RateLimit rateLimiter);

}
