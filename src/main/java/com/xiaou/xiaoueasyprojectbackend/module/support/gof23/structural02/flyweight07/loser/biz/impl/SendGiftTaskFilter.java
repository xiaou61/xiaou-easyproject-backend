package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.biz.impl;



import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.biz.SendGiftMsg;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.biz.TaskFilter;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.flyweight07.loser.core.Component;

import java.util.Objects;

@Component
public class SendGiftTaskFilter implements TaskFilter<SendGiftMsg> {

    @Override
    public boolean doFilter(SendGiftMsg sendGiftMsg) {
        return Objects.isNull(sendGiftMsg) || Objects.isNull(sendGiftMsg.getUserId()) || Objects.isNull(sendGiftMsg.getGiftId());
    }

}
