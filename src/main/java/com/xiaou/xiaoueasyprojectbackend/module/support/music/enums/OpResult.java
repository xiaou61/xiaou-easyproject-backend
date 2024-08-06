package com.xiaou.xiaoueasyprojectbackend.module.support.music.enums;



import java.util.Objects;

/**
 * 业务数据响应体
 *
 * @author: woodwhales on 2020-09-18 22:05
 */
public class OpResult<T> {

    /**
     * 通用基础响应结果对象
     */
    private BaseRespResult baseRespResult;

    /**
     * 业务数据
     */
    private T data;

    public static <T> OpResult<T> success(T data) {
        return new OpResult<T>(RespCodeEnum.SUCCESS, data);
    }

    public static <T> OpResult<T> success() {
        return new OpResult<T>(RespCodeEnum.SUCCESS, null);
    }

    public static <T> OpResult<T> failure() {
        return new OpResult<T>(RespCodeEnum.ERROR, null);
    }

    public static <T> OpResult<T> failure(BaseRespResult baseRespResult) {
        return new OpResult<T>(baseRespResult, null);
    }

    public static <T> OpResult<T> failure(BaseRespResult baseRespResult, T data) {
        return new OpResult<T>(baseRespResult, data);
    }

    /**
     * 获取数据对象
     *
     * @return 数据对象
     */
    public T getData() {
        return data;
    }

    /**
     * 获取响应状态码对象
     *
     * @return 响应状态码对象
     */
    public BaseRespResult getBaseRespResult() {
        return baseRespResult;
    }

    /**
     * 数据是否为空
     *
     * @return 数据是否为空
     */
    public boolean dataIsNull() {
        return Objects.isNull(data);
    }

    /**
     * 数据是否不为空
     *
     * @return 数据是否不为空
     */
    public boolean dataIsNonNull() {
        return Objects.nonNull(data);
    }

    /**
     * 是否响应成功
     *
     * @return 是否响应成功
     */
    public boolean isSuccessful() {
        return Objects.equals(baseRespResult.getCode(), RespCodeEnum.SUCCESS.getCode());
    }

    /**
     * 是否响应失败
     *
     * @return 是否响应失败
     */
    public boolean isFailure() {
        return !isSuccessful();
    }

    private OpResult() {
    }

    private OpResult(BaseRespResult baseRespResult, T data) {
        this.baseRespResult = baseRespResult;
        this.data = data;
    }
}
