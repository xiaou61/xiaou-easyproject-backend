package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.factory02.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.factory02.loser.biz.GoodService;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.factory02.loser.biz.UserService;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.factory02.loser.core.BeanFactory;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.factory02.loser.core.Server;
import org.junit.Assert;
import org.junit.Test;

/**
 * 推荐指数：★★★★★
 * <p>
 * 工厂方法是一种创建型设计模式，其在父类中提供一个创建对象的方法，允许子类决定实例化对象的类型
 * <p>
 * 模拟bean工厂生成不同的bean
 */
public class TestFactory {

    @Test
    public void test() {
        // 同工厂类获取目标对象 屏蔽初始化类的繁琐步骤
        Server goodService = BeanFactory.getBean(GoodService.class);
        Assert.assertTrue(goodService instanceof GoodService);
        Server userService = BeanFactory.getBean(UserService.class);
        Assert.assertTrue(userService instanceof UserService);
    }

}
