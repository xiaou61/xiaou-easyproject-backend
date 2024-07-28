package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.mediator07.loser;

public class Tenant extends Person {

    public Tenant(String name, MediatorCompany mediatorCompany) {
        super(name, mediatorCompany);
    }

    public void connection(String message) {
        mediatorCompany.connection(this, message);
    }

    public void getMessage(String message) {
        System.out.println("租客" + name + "获取到的信息:" + message);
    }

}
