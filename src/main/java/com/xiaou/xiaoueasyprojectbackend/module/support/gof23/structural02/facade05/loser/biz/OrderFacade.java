package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.core.Autowired;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.core.Component;

@Component
public class OrderFacade {

    @Autowired
    private SubRepertorySystem subRepertorySystem;
    @Autowired
    private SubPaySystem subPaySystem;
    @Autowired
    private SubOrderSystem subOrderSystem;

    public void order(Order order) {

        subRepertorySystem.check(order);
        subRepertorySystem.order(order);
        subOrderSystem.createOrder(order);
        subPaySystem.pay(order);

    }

}
