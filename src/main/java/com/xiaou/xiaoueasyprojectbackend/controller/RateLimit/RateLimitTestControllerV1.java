package com.xiaou.xiaoueasyprojectbackend.controller.RateLimit;

import com.xiaou.xiaoueasyprojectbackend.common.annotations.RateLimitKey;
import com.xiaou.xiaoueasyprojectbackend.common.annotations.ratelimit.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rateLimitTest")
public class RateLimitTestControllerV1 {
    @GetMapping("/redis")
    @RateLimit(key = RateLimitKey.TEST_KEY, time = 10, maxCount = 5, cacheType = RateLimit.CacheType.REDIS,
            limitType = RateLimit.LimitType.GLOBAL)
    public String redisTest() {
        return "Redis限流测试";
    }

    @GetMapping("/map")
    @RateLimit(key = RateLimitKey.TEST_KEY, time = 10, maxCount = 5, cacheType = RateLimit.CacheType.Map,
            limitType = RateLimit.LimitType.GLOBAL)
    public String mapindex() {
        return "Map限流测试";
    }
}
