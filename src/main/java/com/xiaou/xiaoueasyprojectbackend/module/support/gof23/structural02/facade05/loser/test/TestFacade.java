package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz.Order;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz.OrderFacade;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.core.ApplicationContext;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.core.ContextUtils;
import org.junit.Test;

/**
 * 推荐指数：★★★★☆
 * <p>
 * 外观是一种结构型设计模式，能为程序库、框架或其他复杂类提供一个简单的接口。
 * <p>
 * 模拟下单场景
 */
public class TestFacade {

    @Test
    public void test() {

        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.start();
        ContextUtils.setApplicationContext(applicationContext);

        OrderFacade orderFacade = ContextUtils.getBean(OrderFacade.class);
        Order order = new Order();
        order.setGoodId(1111L);
        order.setUserId(1001L);
        order.setGoodNum(10);
        orderFacade.order(order);

    }

}
