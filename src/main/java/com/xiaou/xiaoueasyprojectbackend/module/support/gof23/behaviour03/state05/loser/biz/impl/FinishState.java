package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.Task;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.TaskState;

public class FinishState implements TaskState {

    @Override
    public void create(Task task) {
        System.err.println("任务已经开始 不能创建" + task);
    }

    @Override
    public void start(Task task) {
        System.err.println("任务已经开始 不能重复开始" + task);
    }

    @Override
    public void finish(Task task) {
        System.out.println("完成任务" + task);
        task.setState(new OverState());
    }

}
