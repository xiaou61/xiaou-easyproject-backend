package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.strategy02.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.strategy02.loser.biz.RainHandler;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.strategy02.loser.core.ApplicationContext;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.strategy02.loser.core.ContextUtils;
import org.junit.Test;

/**
 * 推荐指数：★★★★★
 * <p>
 * 策略是一种行为设计模式，它能让你定义一系列算法，并将每种算法分别放入独立的类中，以使算法的对象能够相互替换。
 * <p>
 * 模拟红包雨活动 在不同事件下 进行不同操作
 */
public class TestStrategy {

    @Test
    public void test() {

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();
        ContextUtils.setApplicationContext(applicationContext);
        RainHandler rainHandler = ContextUtils.getBean(RainHandler.class);
        rainHandler.start();
        while (true) {

        }

    }

}
