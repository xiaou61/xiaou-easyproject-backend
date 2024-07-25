package com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.implementation;

import cn.hutool.cache.impl.LRUCache;

import com.google.common.util.concurrent.RateLimiter;
import com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.RateLimit;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@SuppressWarnings("UnstableApiUsage")
@Component
@RequiredArgsConstructor
@Slf4j
public class MapRateLimitChecker extends AbstractRateLimitChecker{

    /**
     * 最大仅支持4096个key   超出这个key  限流将可能失效
     */
    private final LRUCache<String, RateLimiter> cache = new LRUCache<>(4096);


    @Override
    public void check(RateLimit rateLimit) {
        String combinedKey = rateLimit.limitType().generateCombinedKey(rateLimit);

        RateLimiter rateLimiter = cache.get(combinedKey,
            () -> RateLimiter.create((double) rateLimit.maxCount() / rateLimit.time())
        );

        if (!rateLimiter.tryAcquire()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"请求过于频繁，请稍后再试");
        }

        log.info("限制请求key:{}, combined key:{}", rateLimit.key(), combinedKey);
    }

}
