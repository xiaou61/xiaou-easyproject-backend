package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.biz.Context;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.core.ApplicationContext;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.core.ContextUtils;
import org.junit.Test;

/**
 * 推荐指数：★★★★★
 * <p>
 * 观察者是一种行为设计模式，允许你定义一种订阅机制，可在对象事件发生时通知多个“观察”该对象的其他对象。
 * <p>
 * 模拟spring容器发布消息
 */
public class TestObserver {

    @Test
    public void test() {

        // 模拟推送消息
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();
        ContextUtils.setApplicationContext(applicationContext);

        Context context = new Context();
        context.setApplicationContext(applicationContext);
        context.setDesc("测试上下文");

        ContextUtils.pushContext(context);

    }

}
