package com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.implementation;

import cn.hutool.core.collection.ListUtil;

import com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.ratelimit.RateLimit;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

/**
 * 限流检查器
 * 使用Redis实现
 * 处理请求限流
 *
 * @作者 valarchie
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RedisRateLimitChecker extends AbstractRateLimitChecker {

    private final RedisTemplate<Object, Object> redisTemplate;

    private final RedisScript<Long> limitScript = new DefaultRedisScript<>(limitScriptText(), Long.class);

    @Override
    public void check(RateLimit rateLimiter) {
        int maxCount = rateLimiter.maxCount();
        String combineKey = rateLimiter.limitType().generateCombinedKey(rateLimiter);

        Long currentCount;
        try {
            currentCount = redisTemplate.execute(limitScript, ListUtil.of(combineKey), maxCount, rateLimiter.time());
            log.info("限制请求:{}, 当前请求次数:{}, 缓存key:{}", combineKey, currentCount, rateLimiter.key());
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw new RuntimeException("redis限流器异常，请确保redis启动正常");
        }

        if (currentCount == null) {
            throw new RuntimeException("redis限流器异常，请稍后再试");
        }

        if (currentCount.intValue() > maxCount) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请求过于频繁，请稍后再试");
        }

    }

    /**
     * 限流脚本
     */
    private static String limitScriptText() {
        return "local key = KEYS[1]\n" +
                "local count = tonumber(ARGV[1])\n" +
                "local time = tonumber(ARGV[2])\n" +
                "if count == nil or time == nil then\n" +
                "    return nil;\n" +
                "end\n" +
                "local current = redis.call('get', key);\n" +
                "if current then\n" +
                "    current = tonumber(current)\n" +
                "    if current and current > count then\n" +
                "        return current;\n" +
                "    end\n" +
                "end\n" +
                "current = redis.call('incr', key)\n" +
                "if tonumber(current) == 1 then\n" +
                "    redis.call('expire', key, time)\n" +
                "end\n" +
                "return current;";
    }


}
