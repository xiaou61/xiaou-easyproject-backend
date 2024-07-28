package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.Message;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.impl.ErrorMessageSender;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.impl.InfoMessageSender;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.impl.msg.SmsMessage;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.bridge04.loser.biz.impl.msg.WxMessage;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 桥接是一种结构型设计模式，可将一个大类或一系列紧密相关的类拆分为抽象和实现两个独立的层次结构，从而能在开发时分别使用。
 * <p>
 * 模拟在不同场景下发送不同级别的消息
 */
public class TestBridge {

    @Test
    public void test() {

        Message message = new Message();
        String target = "loser";

        SmsMessage smsMessage = new SmsMessage();
        InfoMessageSender infoMessageSender = new InfoMessageSender(smsMessage);
        ErrorMessageSender errorMessageSender = new ErrorMessageSender(smsMessage);
        infoMessageSender.send(message, target);
        errorMessageSender.send(message, target);

        WxMessage wxMessage = new WxMessage();
        InfoMessageSender wxInfoMessageSender = new InfoMessageSender(wxMessage);
        ErrorMessageSender wxErrorMessageSender = new ErrorMessageSender(wxMessage);
        wxInfoMessageSender.send(message, target);
        wxErrorMessageSender.send(message, target);

    }

}
