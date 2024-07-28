package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.biz.Context;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.biz.ContextObserver;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.observer06.loser.core.Component;

@Component
public class TaskContextObserver implements ContextObserver {

    @Override
    public void doContext(Context context) {
        System.out.println("TaskContextObserver context = " + context);
    }

}
