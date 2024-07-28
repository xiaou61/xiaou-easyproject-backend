package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.biz.AwardConfig;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.biz.BaseAwardHandler;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.core.Component;

@Component
public class CarAwardHandler extends BaseAwardHandler {

    @Override
    protected void doAwardCore(Long userId, AwardConfig config) {
        System.out.println("send car userId:" + userId + " " + config);
    }

    @Override
    protected int getAwardType() {
        return 2;
    }
}
