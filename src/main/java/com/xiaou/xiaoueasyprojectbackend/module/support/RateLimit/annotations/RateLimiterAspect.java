package com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations;


import com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.RateLimit;
import com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.implementation.MapRateLimitChecker;
import com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.implementation.RedisRateLimitChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 限流切面处理

 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class RateLimiterAspect {

    private final RedisRateLimitChecker redisRateLimitChecker;

    private final MapRateLimitChecker mapRateLimitChecker;


    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimit rateLimiter) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        log.info("当前限流方法:" + method.toGenericString());

        switch (rateLimiter.cacheType()) {
            case REDIS:
                redisRateLimitChecker.check(rateLimiter);
                break;
            case Map:
                mapRateLimitChecker.check(rateLimiter);
                return;
            default:
                redisRateLimitChecker.check(rateLimiter);
        }

    }

}
