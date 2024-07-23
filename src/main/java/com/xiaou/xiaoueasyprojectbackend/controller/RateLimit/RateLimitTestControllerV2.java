package com.xiaou.xiaoueasyprojectbackend.controller.RateLimit;

import com.xiaou.xiaoueasyprojectbackend.common.manager.RedisLimiterManager;
import jakarta.annotation.Resource;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaou61
 * @Date 2024/7/23 16:09
 * @Description: 限流接口-鱼皮 使用redisson来完成 这个比V1版本要简单很多 但是可扩展性比较低
 * 需要有一个配置 {@link com.xiaou.xiaoueasyprojectbackend.common.config.RedissonConfig}
 */
@RestController
@RequestMapping("/v2/rateLimitTest")
public class RateLimitTestControllerV2 {
    @Resource
    private RedisLimiterManager redisLimiterManager;
    @GetMapping

    public String rateLimitTestV2(){
        //判断限流
        redisLimiterManager.doRateLimit("key");
        return "v2限流测试";
    }
}
