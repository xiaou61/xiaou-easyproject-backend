package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz.impl;




import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz.LoginMsg;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.biz.TaskFilter;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.facade05.loser.core.Component;

import java.util.Objects;

@Component
public class LoginTaskFilter implements TaskFilter<LoginMsg> {

    @Override
    public boolean doFilter(LoginMsg loginMsg) {
        return Objects.isNull(loginMsg) || Objects.isNull(loginMsg.getUserId());
    }
}
