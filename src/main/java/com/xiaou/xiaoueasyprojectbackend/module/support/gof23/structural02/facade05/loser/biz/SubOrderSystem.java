package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.core.Component;

@Component
public class SubOrderSystem {

    public void createOrder(Order order) {
        System.out.println("生成订单 " + order);
    }

}
