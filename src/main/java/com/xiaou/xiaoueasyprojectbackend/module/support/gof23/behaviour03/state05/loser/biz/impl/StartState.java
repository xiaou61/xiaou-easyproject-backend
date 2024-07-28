package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.Task;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.TaskState;

public class StartState implements TaskState {

    @Override
    public void create(Task task) {
        System.err.println("任务已创建 不能重复创建" + task);
    }

    @Override
    public void start(Task task) {
        System.out.println("任务开始" + task);
        task.setState(new FinishState());
    }

    @Override
    public void finish(Task task) {
        System.err.println("任务未开始 不能完成" + task);
    }

}
