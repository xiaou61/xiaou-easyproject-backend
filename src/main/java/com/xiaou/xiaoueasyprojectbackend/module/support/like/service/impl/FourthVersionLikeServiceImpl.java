package com.xiaou.xiaoueasyprojectbackend.module.support.like.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.entity.Like;
import com.xiaou.xiaoueasyprojectbackend.module.support.captcha.utils.RedisCache;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.mapper.LikeMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.service.LikeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FourthVersionLikeServiceImpl extends ServiceImpl<LikeMapper, Like>
        implements LikeService {

    @Resource
    private RedisCache redisUtil;

    @Override
    public boolean like(Integer userId, Integer productId) {
        List<String> keys = new ArrayList<>();
        keys.add(buildUserRedisKey(userId));
        keys.add(buildProductRedisKey(productId));

        int value1 = 1;

        redisUtil.execute("lua-script/like.lua", keys, value1);

        return true;
    }

    @Override
    public boolean unlike(Integer userId, Integer productId) {
        List<String> keys = new ArrayList<>();
        keys.add(buildUserRedisKey(userId));
        keys.add(buildProductRedisKey(productId));

        int value1 = 0;

        redisUtil.execute("lua-script/like.lua", keys, value1);

        return true;
    }

    @Override
    public Integer getProductLikeCount(Integer productId) {
        String key = buildProductRedisKey(productId);
        Integer count = (Integer) redisUtil.get(key);

        return count == null ? 0 : count;
    }

    @Override
    public boolean isLiked(Integer userId, Integer productId) {
        String userKey = buildUserRedisKey(userId);
        String productKey = buildProductRedisKey(productId);

        return redisUtil.sHasKey(userKey, productKey);
    }


    private String buildUserRedisKey(Integer userId) {
        return "userId_" + userId;
    }

    private String buildProductRedisKey(Integer productId) {
        return "productId_" + productId;
    }
}
