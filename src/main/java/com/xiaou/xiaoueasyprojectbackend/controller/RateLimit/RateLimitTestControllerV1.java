package com.xiaou.xiaoueasyprojectbackend.controller.RateLimit;

import com.xiaou.xiaoueasyprojectbackend.common.annotations.RateLimitKey;
import com.xiaou.xiaoueasyprojectbackend.common.annotations.ratelimit.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author xiaou61
 * @Date 2024/7/23 16:06
 * @Description: 限流接口 aop Lua脚本 注解实现 实现起来比较复杂
 * 如果想要迁移直接迁移{@link RateLimit 这个注释所在的ratelimit文件夹里所有内容即可}
*/
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
