package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.impl;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.AbstractMessage;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Imessage;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Message;

public class ErrorMessageSender extends AbstractMessage {

    public ErrorMessageSender(Imessage imessage) {
        super(imessage);
    }

    @Override
    public void send(Message message, String target) {
        System.out.println("紧急错误消息");
        super.send(message, target);
    }

}
