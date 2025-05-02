package com.xiaou.hot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaou.hot.mapper.HotPostMapper;
import com.xiaou.hot.model.po.HotPost;
import com.xiaou.hot.model.vo.HotPostVO;
import com.xiaou.hot.service.HotPostService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class HotPostServiceImpl extends ServiceImpl<HotPostMapper, HotPost> implements HotPostService {


    private static final String HOT_POST_CACHE_KEY = "hot_post_list";
    // 缓存时间 30 分钟
    private static final long CACHE_EXPIRE_TIME = 30;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public List<HotPostVO> getHotPostList() {
        // 1. 尝试从 Redis 获取数据
        String hotPostJson = (String) redisTemplate.opsForValue().get(HOT_POST_CACHE_KEY);
        if (hotPostJson != null) {
            try {
                return objectMapper.readValue(hotPostJson, new TypeReference<List<HotPostVO>>() {
                });
            } catch (Exception e) {
                log.error("从 Redis 读取热点文章列表失败", e);
            }
        }

        // 2. 如果 Redis 没有数据，则查询数据库
        List<HotPostVO> hotPostList = this.list(new LambdaQueryWrapper<HotPost>().orderByAsc(HotPost::getSort))
                .stream().map(HotPostVO::objToVo).collect(Collectors.toList());

        // 3. 将查询结果存入 Redis，并设置过期时间
        try {
            redisTemplate.opsForValue().set(HOT_POST_CACHE_KEY, objectMapper.writeValueAsString(hotPostList), CACHE_EXPIRE_TIME, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("缓存热点文章列表失败", e);
        }

        // 4. 返回数据
        return hotPostList;
    }
}


