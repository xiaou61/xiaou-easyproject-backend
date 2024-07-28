package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.adapter.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.adapter.TaskContext;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.adapter.TaskContextAdapter;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.mq.OnlineMsg;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.core.Component;

@Component
public class OnlineMsgAdapter implements TaskContextAdapter<OnlineMsg> {

    @Override
    public TaskContext adapter(int actId, OnlineMsg msg) {
        TaskContext taskContext = new TaskContext();
        taskContext.setActId(actId);
        taskContext.setUserId(msg.getUserId());
        taskContext.setTime(msg.getOnlineTime());
        taskContext.setNum(1);
        taskContext.setType(2);
        return taskContext;
    }

}
