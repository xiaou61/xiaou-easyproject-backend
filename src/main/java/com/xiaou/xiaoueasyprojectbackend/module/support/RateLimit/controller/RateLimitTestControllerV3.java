package com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.controller;

import com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.annotations.Limit;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaou61
 * @Date 2024/7/23 16:09
 * @Description: 限流接口 Guava实现
 */
@RestController
@RequestMapping("/v3/rateLimitTest")
@Slf4j
@Tag(name = "限流接口V3", description = "限流接口 Guava实现")
public class RateLimitTestControllerV3 {
    @GetMapping("/test")
    @Limit(key = "limit3", permitsPerSecond = 1, timeout = 50, timeunit = TimeUnit.MILLISECONDS,msg = "系统繁忙，请稍后再试！")
    public String limit3() {
        log.info("令牌桶limit3获取令牌成功");
        return "ok";
    }
}
