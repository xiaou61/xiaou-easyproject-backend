package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.core.Component;

@Component
public class CommandInvoker {

    public void execute(Command command) {
        System.out.println(command.getClass());
        System.out.println(command);
    }

}
