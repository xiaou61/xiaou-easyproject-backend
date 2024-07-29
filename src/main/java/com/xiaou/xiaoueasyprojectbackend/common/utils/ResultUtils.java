package com.xiaou.xiaoueasyprojectbackend.common.utils;

import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.BaseResponse;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;

/**
 * 返回工具类

 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }
    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<>(0, data, "error");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }


}
