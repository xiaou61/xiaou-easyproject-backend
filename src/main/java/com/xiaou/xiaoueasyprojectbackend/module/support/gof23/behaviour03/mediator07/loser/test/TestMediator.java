package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.mediator07.loser.test;

import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.mediator07.loser.HouseOwner;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.mediator07.loser.MediatorCompany;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.mediator07.loser.Tenant;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 中介者是一种行为设计模式，能让你减少对象之间混乱无序的依赖关系。该模式会限制对象之间的直接交互，迫使它们通过一个中介者对象进行合作。
 * <p>
 * 模拟用户找中介公司租房子
 */
public class TestMediator {

    @Test
    public void test() {
        // 先创建三个角色，中介公司，房主，租客
        MediatorCompany mediatorCompany = new MediatorCompany();
        // 房主和租客都在同一家中介公司
        HouseOwner houseOwner = new HouseOwner("大白", mediatorCompany);
        Tenant tenant = new Tenant("小白", mediatorCompany);
        // 中介公司获取房主和租客的信息
        mediatorCompany.setHouseOwner(houseOwner);
        mediatorCompany.setTenant(tenant);
        // 房主和租客都在这家中介公司发布消息，获取到对应的消息
        tenant.connection(tenant.getName() + "想租一房一厅!");
        houseOwner.connection(houseOwner.getName() + "这里有!来看看呗!");

    }
}
