package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.core.Component;

@Component
public class SubRepertorySystem {

    public void check(Order order) {
        int num = 1000;
        System.out.println("检查库存 " + num);
    }

    public void order(Order order) {
        System.out.println("扣减库存 " + order.getGoodNum());
    }

}
