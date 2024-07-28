package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.impl.msg;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Imessage;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Message;

public class WxMessage implements Imessage {

    @Override
    public void send(Message message, String target) {
        System.out.println("发送消息到企业微信群");
        System.out.println();
    }

}
