package com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.enums;


public enum NumberEnums {

    /**
     *
     */
    ZERO(0),
    /**
     * 1
     */
    ONE(1),

    /**
     * 3
     */
    THREE(3);

    private final int code;

    NumberEnums(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
