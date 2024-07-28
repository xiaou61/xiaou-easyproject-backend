package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.biz.Command;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.biz.CommandInvoker;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.command03.loser.core.Autowired;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.core.Component;
import lombok.Data;

@Data
@Component
public class BizCommand implements Command {

    private final String desc = "业务命令";

    @Autowired
    private CommandInvoker commandInvoker;

    @Override
    public void execute() {
        commandInvoker.execute(this);
    }

}
