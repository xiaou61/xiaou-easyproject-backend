package com.xiaou.xiaoueasyprojectbackend.module.support.log.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 响应对象。包含处理结果（Meta）和返回数据（Data）两部分，在 Controller 处理完请求后将此对象转换成 json 返回给前台。注意：
 * <ul>
 * <li>处理成功一般返回处理结果和返回数据，失败只返回处理结果。具体返回什么需看接口文档。</li>
 * <li>处理成功结果码一般是200，失败码具体看出了什么错，对照 HTTP 响应码填。</li>
 * <li>默认处理方法慎用，前台最想要拿到的还是具体的结果码和信息。</li>
 * </ul>
 * <p>
 * @author xiaohai
 * @date 2022/04/06 10:11
 */
@Component
@Scope("prototype")
@SuppressWarnings(value = "all")
@Data
@AllArgsConstructor
public class Response<T> {

    /**
     * 默认成功响应码
     */
    private static final Integer DEAFAULT_SUCCESS_CODE = HttpStatus.OK.value();
    /**
     * 默认成功响应信息
     */
    private static final String DEAFAULT_SUCCESS_MSG = "请求/处理成功！";
    /**
     * 默认失败响应码
     */
    private static final Integer DEAFAULT_FAILURE_CODE = HttpStatus.BAD_REQUEST.value();
    /**
     * 默认失败响应信息
     */
    private static final String DEAFAULT_FAILURE_MSG = "请求/处理失败！";

    /**
     * 处理结果代码，与 HTTP 状态响应码对应
     */
    @Schema(description = "处理结果代码", example = "200/400")
    private Integer code;

    /**
     * 处理结果信息
     */
    @Schema(description = "处理结果信息", example = "描述信息")
    private String msg;

    @Schema(description = "数据信息", example = "返回数据")
    private T data;

    public Response() {

    }

    public Response(HttpStatus httpStatus, String msg, T data) {
        this.code = httpStatus.value();
        this.msg = msg;
        this.data = data;
    }

    /*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓成功↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     *
     * @param msg  处理结果信息
     * @return 响应对象
     * <p>
     * @author xiaohai
     * @date 2022/04/06 10:11
     */
    public static <T> Response<T> success(String msg) {
        return new Response<>(DEAFAULT_SUCCESS_CODE, msg, null);
    }

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     *
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author xiaohai
     * @date 2022/04/06 10:11
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(DEAFAULT_SUCCESS_CODE, DEAFAULT_SUCCESS_MSG, data);
    }

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     *
     * @param msg  处理结果信息
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author xiaohai
     * @date 2022/04/06 10:11
     */
    public static <T> Response<T> success(String msg, T data) {
        return new Response<> (DEAFAULT_SUCCESS_CODE, msg, data);
    }

    /**
     * 处理成功响应，返回自定义响应码、信息和数据。
     *
     * @param httpStatus HTTP 响应码
     * @param msg  处理结果信息
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author xiaohai
     * @date 2022/04/06 10:11
     */
    public static <T> Response<T> success(HttpStatus httpStatus, String msg, T data) {
        return new Response<>(httpStatus, msg, data);
    }


    /*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓失败↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     *
     * @param msg 处理结果信息
     * @return 响应对象
     * <p>
     * @author xiaohai
     * @date 2022/04/06 10:11
     */
    public static <T>  Response<T> failure(String msg) {
        return new Response<>(DEAFAULT_FAILURE_CODE, msg,null);
    }

    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     *
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author xiaohai
     * @date 2022/04/06 10:11
     */
    public static <T> Response<T> failure(T data) {
        return new Response<>(DEAFAULT_FAILURE_CODE, DEAFAULT_FAILURE_MSG, data);
    }

    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     *
     * @param msg  处理结果信息
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author xiaohai
     * @date 2022/04/06 10:11
     */
    public static<T> Response<T> failure(String msg, T data) {
        return new Response<>(DEAFAULT_FAILURE_CODE, msg, data);
    }

    /**
     * 处理失败响应，返回自定义响应码、信息和数据。
     *
     * @param httpStatus HTTP 响应码
     * @param msg  处理结果信息
     * @param data 返回数据
     * @return 响应对象
     * <p>
     * @author xiaohai
     * @date 2022/04/06 10:11
     */

    public static<T> Response<T> failure(HttpStatus httpStatus, String msg, T data) {
        return new Response<>(httpStatus.value(), msg, data);
    }

    /**
     * 返回错误消息
     * @param httpStatus
     * @param msg
     * @return
     * @param <T>
     */
    public static<T> Response<T> failure(HttpStatus httpStatus, String msg) {
        return new Response<>(httpStatus.value(), msg, null);
    }


}
