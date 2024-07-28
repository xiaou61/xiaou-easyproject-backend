package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.mediator07.loser;

import lombok.Data;

@Data
public class MediatorCompany {
    private HouseOwner houseOwner;
    private Tenant tenant;

    public void connection(Person person, String message) {
        // 房主需要通过中介获取租客信息
        if (person.equals(houseOwner)) {
            this.tenant.getMessage(message);
        } else { // 反之租客通过中介获取房主信息
            this.houseOwner.getMessage(message);
        }
    }
}