package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.Task;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.TaskState;

public class OverState implements TaskState {

    @Override
    public void create(Task task) {
        System.err.println("任务已经完成 不能操作" + task);
    }

    @Override
    public void start(Task task) {
        System.err.println("任务已经完成 不能操作" + task);
    }

    @Override
    public void finish(Task task) {
        System.err.println("任务已经完成 不能操作" + task);
    }

}
