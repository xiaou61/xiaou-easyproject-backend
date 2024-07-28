package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.mediator07.loser;

public class HouseOwner extends Person {
    public HouseOwner(String name, MediatorCompany mediatorCompany) {
        super(name, mediatorCompany);
    }

    // 联络方法
    public void connection(String message) {
        mediatorCompany.connection(this, message);
    }

    // 获取消息
    public void getMessage(String message) {
        System.out.println("房主" + name + "获取到的信息:" + message);
    }
}