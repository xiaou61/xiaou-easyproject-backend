package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.biz.AwardProcess;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.core.ApplicationContext;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.core.ContextUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * 推荐指数：★★★★☆
 * <p>
 * 模板方法是一种行为设计模式，它在超类中定义了一个算法的框架，允许子类在不修改结构的情况下重写算法的特定步骤。
 * <p>
 * 模拟发送营收奖励 按照指定模板执行
 */
public class TestTemplate {

    @Test
    public void test() {

        // 模拟发送营收奖励 按照指定模板执行
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();
        ContextUtils.setApplicationContext(applicationContext);

        AwardProcess awardProcess = ContextUtils.getBean(AwardProcess.class);
        awardProcess.doAward(1001L, Arrays.asList("A101", "A102", "A103"));

    }

}
