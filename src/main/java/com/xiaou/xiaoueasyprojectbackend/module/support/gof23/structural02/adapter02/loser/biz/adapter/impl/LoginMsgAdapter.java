package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.adapter.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.adapter.TaskContext;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.adapter.TaskContextAdapter;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.mq.LoginMsg;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.core.Component;

@Component
public class LoginMsgAdapter implements TaskContextAdapter<LoginMsg> {

    @Override
    public TaskContext adapter(int actId, LoginMsg msg) {
        TaskContext taskContext = new TaskContext();
        taskContext.setActId(actId);
        taskContext.setUserId(msg.getUid());
        taskContext.setTime(msg.getLoginTime().getTime());
        taskContext.setNum(1);
        taskContext.setType(1);
        return taskContext;
    }

}
