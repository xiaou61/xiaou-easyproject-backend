package com.xiaou.xiaoueasyprojectbackend.module.support.ping.controller;

import com.xiaou.xiaoueasyprojectbackend.module.support.ping.model.PingResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author xiaou61
 * @Date 2024/8/09 15:33
 * @Description: 连通性测试控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ping")
public class PingController {
    private final JdbcTemplate jdbcTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    /**
     * 测试 MySQL 的延迟（单位：毫秒）
     *
     * @date 2024/6/30
     * @since 2.3.0
     */
    @GetMapping("/ping/mysql")
    public PingResultVO pingMysql() {
        long startTime = System.currentTimeMillis();
        jdbcTemplate.execute("SELECT 1");
        long endTime = System.currentTimeMillis();

        return PingResultVO.builder().delay(endTime - startTime).build();
    }

    /**
     * 测试 Redis 的延迟（单位：毫秒）
     *
     * @date 2024/6/30
     * @since 2.3.0
     */
    @GetMapping("/ping/redis")
    public PingResultVO pingRedis() {
        long startTime = System.currentTimeMillis();
        stringRedisTemplate
                .opsForValue()
                .set("temp:last-ping", LocalDateTime.now().toString(), Duration.ofMinutes(1L));
        long endTime = System.currentTimeMillis();

        return PingResultVO.builder().delay(endTime - startTime).build();
    }
}
