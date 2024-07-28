package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.biz.impl.BizCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.biz.impl.ConcreteCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.core.ApplicationContext;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.core.ContextUtils;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 命令是一种行为设计模式，它可将请求转换为一个包含与请求相关的所有信息的独立对象。该转换让你能根据不同的请求将方法参数化、延迟请求执行或将其放入队列中，且能实现可撤销操作。
 * <p>
 * 模拟简单命令
 */
public class TestCommand {

    @Test
    public void test() {

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();
        ContextUtils.setApplicationContext(applicationContext);

        ConcreteCommand concreteCommand = ContextUtils.getBean(ConcreteCommand.class);
        concreteCommand.execute();

        BizCommand bizCommand = ContextUtils.getBean(BizCommand.class);
        bizCommand.execute();

    }

}
