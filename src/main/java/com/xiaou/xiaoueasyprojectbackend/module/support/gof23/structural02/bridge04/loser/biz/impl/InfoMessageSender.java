package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.AbstractMessage;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Imessage;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Message;

public class InfoMessageSender extends AbstractMessage {

    public InfoMessageSender(Imessage imessage) {
        super(imessage);
    }

    @Override
    public void send(Message message, String target) {
        System.out.println("普通提示消息");
        super.send(message, target);
    }

}
