package com.xiaou.xiaoueasyprojectbackend.common.manager;


import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;

import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import jakarta.annotation.Resource;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
/**
 * 专门提供限流器的管理器(提供了一个通用的能力)
 */
public class RedisLimiterManager {
    private static final Logger log = LoggerFactory.getLogger(RedisLimiterManager.class);
    @Resource
    private RedissonClient redissonClient;

    public void doRateLimit(String key) {
        RRateLimiter rRateLimiter = redissonClient.getRateLimiter(key);
        //rate是每个时间单位允许访问几次 rateInterval就是时间单位 10s内访问2次
        rRateLimiter.trySetRate(RateType.PER_CLIENT, 2, 10, RateIntervalUnit.SECONDS);
        //每当一个操作来了之后,去请求一个令牌
        boolean canOp = rRateLimiter.tryAcquire(1);
        if (!canOp) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "当前访问过于频繁");
        }
    }
}
