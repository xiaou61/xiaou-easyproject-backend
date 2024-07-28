package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.Task;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.TaskState;

public class CreateState implements TaskState {

    @Override
    public void create(Task task) {
        System.out.println("任务创建" + task);
        task.setState(new StartState());
    }

    @Override
    public void start(Task task) {
        System.err.println("任务未创建 不能开始" + task);
    }

    @Override
    public void finish(Task task) {
        System.err.println("任务未创建 不能完成" + task);
    }

}
