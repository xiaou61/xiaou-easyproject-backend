package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.mediator07.loser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    protected String name;
    protected MediatorCompany mediatorCompany;
}