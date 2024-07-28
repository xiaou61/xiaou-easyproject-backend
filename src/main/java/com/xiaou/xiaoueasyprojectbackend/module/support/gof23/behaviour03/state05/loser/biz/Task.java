package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.impl.CreateState;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Task {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private String orderId;

    private TaskState state = new CreateState();

    public Task(String orderId) {
        this.orderId = orderId;
    }

    public void create() {
        atomicInteger.getAndIncrement();
        state.create(this);
    }

    public void start() {
        atomicInteger.getAndIncrement();
        state.start(this);
    }

    public void finish() {
        atomicInteger.getAndIncrement();
        state.finish(this);
    }

}
