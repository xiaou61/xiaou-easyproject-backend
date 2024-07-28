package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.biz;

import cn.hutool.core.util.RandomUtil;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.core.Component;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.core.ContextUtils;


import java.util.List;
import java.util.Objects;

@Component
public class AwardProcess {

    private BaseAwardHandler getByType(int type) {
        List<BaseAwardHandler> awardHandlers = ContextUtils.getBeans(BaseAwardHandler.class);
        for (BaseAwardHandler awardHandler : awardHandlers) {
            if (awardHandler.getAwardType() == type) {
                return awardHandler;
            }
        }
        return null;
    }

    private AwardConfig checkAndChange(String awardId) {

        if (awardId == null) {
            throw new RuntimeException("awardId is empty");
        }

        // 模拟通过db 或者rpc 进行转换
        AwardConfig config = new AwardConfig();
        config.setAwardId(awardId);
        config.setAwardNum(1);
        config.setAwardDay(1);
        config.setBizId(awardId);
        config.setType(RandomUtil.randomInt(2) + 1);
        return config;

    }

    public void doAward(long userId, List<String> awardIds) {

        for (String awardId : awardIds) {
            AwardConfig awardConfig = checkAndChange(awardId);
            BaseAwardHandler awardHandler = getByType(awardConfig.getType());
            if (Objects.nonNull(awardHandler)) {
                awardHandler.doAward(userId, awardConfig);
            }
        }

    }

}
