package com.xiaou.xiaoueasyprojectbackend.module.support.RateLimit.vo;

/**
 * 状态码集合
 *

 * @date 2023/4/12 14:33
 */
public enum ReturnCode {
    /**
     * 操作成功
     **/
    RC100(100, "操作成功"),
    /**
     * 操作失败
     **/
    RC999(999, "操作失败"),

    /**
     * 服务限流
     **/
    LIMIT_ERROR(2001, "系统繁忙,请稍后再试!"),

    /**
     * 服务异常
     **/
    RC500(500, "系统异常，请稍后重试"),

    ILLEGAL_ARGUMENT(3001, "非法参数"),

    ILLEGAL_HEADER(4001, "非法请求头,请添加合适的签名"),

    REPLAY_ERROR(4002, "访问已过期,请重新访问!"),

    ARGUMENT_ERROR(4003, "您正在尝试恶意访问，已记录IP备案！");


    /**
     * 自定义状态码
     **/
    private final int code;
    /**
     * 自定义描述
     **/
    private final String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
