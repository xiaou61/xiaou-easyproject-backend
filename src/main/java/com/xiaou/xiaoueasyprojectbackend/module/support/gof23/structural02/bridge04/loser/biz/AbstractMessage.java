package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz;

public abstract class AbstractMessage {

    protected Imessage imessage;

    public AbstractMessage(Imessage imessage) {
        this.imessage = imessage;
    }

    public void send(Message message, String target) {
        imessage.send(message, target);
    }

}
