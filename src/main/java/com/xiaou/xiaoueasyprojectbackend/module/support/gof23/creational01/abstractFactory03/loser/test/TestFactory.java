package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.biz.GoodService;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.biz.GoodServiceBeanFactory;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.biz.UserService;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.biz.UserServiceBeanFactory;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.core.BeanFactory;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.core.Server;

import org.junit.Assert;
import org.junit.Test;

/**
 * 推荐指数：★★★★★
 * <p>
 * 抽象工厂是一种创建型设计模式，它能创建一系列相关的对象，而无需指定其具体类。
 * <p>
 * 模拟不同的抽象bean工厂生成bean
 */
public class TestFactory {

    @Test
    public void test() {

        // 通过抽象工厂类 扩展新类型的时候 符合开闭原则 只新增新的类 不修改旧的类
        BeanFactory goodServiceBeanFactory = new GoodServiceBeanFactory();
        Server server = goodServiceBeanFactory.getBean();
        Assert.assertTrue(server instanceof GoodService);

        BeanFactory userServiceBeanFactory = new UserServiceBeanFactory();
        Server server1 = userServiceBeanFactory.getBean();
        Assert.assertTrue(server1 instanceof UserService);

    }

}
