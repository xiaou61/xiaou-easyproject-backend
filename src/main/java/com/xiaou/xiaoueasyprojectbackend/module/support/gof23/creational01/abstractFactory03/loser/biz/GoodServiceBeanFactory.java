package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.core.BeanFactory;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.abstractFactory03.loser.core.Server;

public class GoodServiceBeanFactory implements BeanFactory {

    @Override
    public Server getBean() {
        return new GoodService();
    }

}
