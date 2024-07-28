package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.core.Component;

@Component
public class SubPaySystem {

    public void pay(Order order) {
        System.out.println("支付订单 " + order);
    }

}
