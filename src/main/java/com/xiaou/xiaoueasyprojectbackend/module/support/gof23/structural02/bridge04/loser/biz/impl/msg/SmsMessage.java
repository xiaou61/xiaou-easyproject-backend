package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.impl.msg;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Imessage;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Message;

public class SmsMessage implements Imessage {

    @Override
    public void send(Message message, String target) {
        System.out.println("发送消息到指定用户手机");
        System.out.println();
    }

}
