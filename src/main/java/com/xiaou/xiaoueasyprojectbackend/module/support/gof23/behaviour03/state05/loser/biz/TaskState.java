package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz;

public interface TaskState {

    void create(Task task);

    void start(Task task);

    void finish(Task task);

}
