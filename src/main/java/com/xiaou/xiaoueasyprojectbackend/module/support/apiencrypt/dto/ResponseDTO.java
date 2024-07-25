package com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 响应信息主体
 *
 * @author valarchie
 */
@Data
@AllArgsConstructor
public class ResponseDTO<T> {

    private Integer code;

    private String msg;

    @JsonInclude
    private T data;


    public static <T> ResponseDTO<T> ok(T data) {
        return build(data, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage());
    }


    public static <T> ResponseDTO<T> build(T data, Integer code, String msg) {
        return new ResponseDTO<>(code, msg, data);
    }
    
}

