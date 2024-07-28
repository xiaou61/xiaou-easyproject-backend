package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.builder05.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.builder05.loser.biz.User;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.builder05.loser.core.Cache;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.builder05.loser.core.MyCacheBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * 推荐指数：★★★★☆
 * 生成器是一种创建型设计模式，使你能够分步骤创建复杂对象。该模式允许你使用相同的创建代码生成不同类型和形式的对象。
 * <p>
 * 模拟如何使用builder构建本地缓存
 */
public class TestBuilder {

    @Test
    public void test() {

        // 模拟本地缓存的构建
        Cache<Long, User> cache = MyCacheBuilder.newBuilder()
                .name("loser")
                .size(100)
                .build();

        User loser = new User();
        loser.setUserId(111L);
        loser.setName("loser");
        cache.put(loser.getUserId(), loser);
        Assert.assertEquals(cache.get(loser.getUserId()), cache.get(loser.getUserId()));

    }

}
