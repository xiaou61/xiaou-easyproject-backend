package com.xiaou.xiaoueasyprojectbackend.module.support.question.util;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**

 * @Version
 * @Date 2024/6/9 11:03 PM
 */
@Slf4j
@Component
public class CacheClient {


    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private Random random = new Random();


    /**
     * 批量从Redis获取缓存对象
     * @param keys 缓存键列表（注意这里的key类型为Integer，实际Redis中key为String，因此在使用时需要转换）
     * @param clazz 缓存对象的类型
     * @return 包含键值对的Map，未找到的键不会包含在内
     */
    public <T> Map<Integer, T> batchGet(String prefix, List<Integer> keys, Class<T> clazz) {
        try {
            // 将Integer类型的keys转换为String类型，并加上前缀，因为Redis的key是字符串
            List<String> stringKeys = keys.stream()
                    .map(key -> prefix + key.toString())
                    .collect(Collectors.toList());

            // 执行批量获取操作
            List<String> values = stringRedisTemplate.opsForValue().multiGet(stringKeys);

            Map<Integer, T> resultMap = new HashMap<>();
            for (int i = 0; i < stringKeys.size(); i++) {
                String value = values.get(i);
                if (value != null) {
                    // 反序列化字符串为对象
                    T deserializedObject = deserialize(value, clazz);
                    resultMap.put(keys.get(i), deserializedObject);
                }
            }
            return resultMap;
        } catch (Exception e) {
            // 异常处理逻辑，根据需要进行日志记录或抛出异常
            throw new RuntimeException("Failed to batch get from Redis", e);
        }
    }

    /**
     * 简化的对象反序列化示例，根据实际情况调整
     */
    private static <T> T deserialize(String value, Class<T> clazz) {
        // 这里需要根据您的序列化方式来实现，例如使用JSON库如Jackson、Gson等
        // 以下仅为示意，实际请使用正确的反序列化逻辑
        return JSONUtil.toBean(value, clazz); // 假设使用了fastjson库
    }


    public <K, V> void batchPut(String prefix, Map<K, V> data, long expireTime, TimeUnit timeUnit) {
        List<String> allArgs = new ArrayList<>();
        for (Map.Entry<K, V> entry : data.entrySet()) {
            String prefixedKey = prefix + entry.getKey().toString();
            allArgs.add(prefixedKey);
            allArgs.add(JSONUtil.toJsonStr(entry.getValue()));

            // 在放置每个值之后立即设置过期时间
            stringRedisTemplate.opsForValue().set(prefixedKey, JSONUtil.toJsonStr(entry.getValue()), expireTime+(1L+random.nextInt(20)), timeUnit);
        }
    }

}
