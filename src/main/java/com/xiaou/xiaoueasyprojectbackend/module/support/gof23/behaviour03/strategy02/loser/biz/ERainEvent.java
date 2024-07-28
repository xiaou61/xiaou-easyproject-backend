package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.strategy02.loser.biz;

public enum ERainEvent {

    INIT(1, "红包雨初始化"),
    START(2, "红包雨开始"),
    END(3, "红包雨结束"),

    ;

    private final int status;
    private final String desc;

    ERainEvent(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
