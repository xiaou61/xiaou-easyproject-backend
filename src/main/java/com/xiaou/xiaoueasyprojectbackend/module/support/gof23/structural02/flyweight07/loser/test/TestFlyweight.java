package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.biz.LoginMsg;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.biz.SendGiftMsg;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.biz.TaskFilter;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.biz.TaskFilterFactory;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.core.ApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * 推荐指数：★★★★☆
 * <p>
 * 享元是一种结构型设计模式，它摒弃了在每个对象中保存所有数据的方式，通过共享多个对象所共有的相同状态，让你能在有限的内存容量中载入更多对象。
 * <p>
 * 模拟做任务获取不同消息类型的任务过滤器
 */
public class TestFlyweight {

    @Test
    public void test() {

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();

        SendGiftMsg sendGiftMsg = new SendGiftMsg();
        sendGiftMsg.setUserId(0L);
        sendGiftMsg.setAnchorId(0L);
        sendGiftMsg.setGiftId(0L);
        sendGiftMsg.setSendGiftTime(0L);

        LoginMsg loginMsg = new LoginMsg();
        loginMsg.setUserId(0L);
        loginMsg.setLoginTime(0L);

        TaskFilter<SendGiftMsg> sendGiftMsgTaskFilter = TaskFilterFactory.getFilter(SendGiftMsg.class);
        TaskFilter<SendGiftMsg> sendGiftMsgTaskFilter1 = TaskFilterFactory.getFilter(SendGiftMsg.class);
        Assert.assertEquals(sendGiftMsgTaskFilter1, sendGiftMsgTaskFilter);
        System.out.println(sendGiftMsgTaskFilter.doFilter(sendGiftMsg));

        TaskFilter<LoginMsg> loginMsgTaskFilter = TaskFilterFactory.getFilter(LoginMsg.class);
        TaskFilter<LoginMsg> loginMsgTaskFilter1 = TaskFilterFactory.getFilter(LoginMsg.class);
        Assert.assertEquals(loginMsgTaskFilter1, loginMsgTaskFilter);
        System.out.println(loginMsgTaskFilter.doFilter(loginMsg));

    }

}
