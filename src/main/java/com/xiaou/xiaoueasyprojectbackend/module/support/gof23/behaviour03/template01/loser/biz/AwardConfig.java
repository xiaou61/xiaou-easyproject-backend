package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.biz;

import lombok.Data;

@Data
public class AwardConfig {

    private String awardId;

    private int awardNum;

    private int awardDay;

    private String bizId;

    /**
     * 奖励类型
     */
    private int type;

}
