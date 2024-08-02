package com.xiaou.xiaoueasyprojectbackend.module.support.sign.service;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.BitSet;

@Service
public class SignInService {
    @Resource
    private RedisTemplate<String,  byte[]> redisTemplate;
    // 签到
    public void signIn(long userId) {
        LocalDate today = LocalDate.now();
        int dayOfYear = today.getDayOfYear();
        // 使用用户ID和年份作为key，确保每年的数据是独立的
        String key = "sign_in:" + userId + ":" + today.getYear();
        redisTemplate.opsForValue().setBit(key, dayOfYear, true);
    }
    
    // 获取今天签到的人数
    public long countSignInToday() {
        LocalDate today = LocalDate.now();
        return countSignInOnDate(today);
    }
    // 获取本周签到的人数
    public long countSignInThisWeek() {
        LocalDate today = LocalDate.now();
        // 获取本周的开始和结束日期
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return countSignInBetweenDates(startOfWeek, endOfWeek);
    }
    // 获取本月签到的人数
    public long countSignInThisMonth() {
        // 获取本月的开始和结束日期
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return countSignInBetweenDates(startOfMonth, endOfMonth);
    }
    // 获取特定日期签到的人数
    private long countSignInOnDate(LocalDate date) {
        int dayOfYear = date.getDayOfYear();
        String keyPattern = "sign_in:*:" + date.getYear();
        return redisTemplate.keys(keyPattern).stream()
                .filter(key -> redisTemplate.opsForValue().getBit(key, dayOfYear))
                .count();
    }
    // 获取日期范围内签到的人数
    private long countSignInBetweenDates(LocalDate start, LocalDate end) {
        long count = 0;
        // 遍历日期范围
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            count += countSignInOnDate(date);
        }
        return count;
    }
    // 将字节数组反序列化为字符串
    public String deserializeByteArray(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return new String(data, StandardCharsets.UTF_8);
    }

    /**
     * 有坑注意：RedisTemplate<String, byte[]>，这里要留意，如果你的bean中序列化Value的时候用的非字节数组，可能会报错如下
     * @param userId
     * @return
     */
    // 在 countUserSignInThisMonth 方法中的示例用法
    public long countUserSignInThisMonth(long userId) {
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        String key = "sign_in:" + userId + ":" + LocalDate.now().getYear();
        byte[] bitmap = redisTemplate.opsForValue().get(key);

        // 反序列化并将字节数组转换为 BitSet
        String bitmapString = deserializeByteArray(bitmap);
        if (bitmapString == null) {
            return 0;
        }
        BitSet bitSet = BitSet.valueOf(bitmapString.getBytes(StandardCharsets.UTF_8));

        long count = 0;
        for (int day = startOfMonth.getDayOfYear(); day <= endOfMonth.getDayOfYear(); day++) {
            count += bitSet.get(day) ? 1 : 0;
        }
        return count;
    }

}