package com.xiaou.xiaoueasyprojectbackend.common.enums;

//枚举类
public enum Renum {
    //这里是可以自己定义的，方便与前端交互即可
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(200,"成功"),
    ;
    private Integer code;
    private String msg;

  Renum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
