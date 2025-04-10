package org.dromara.weixinqrcode.util;


import com.alibaba.fastjson.JSON;
import org.dromara.weixinqrcode.model.ApiResult;


public class ApiResultUtil {

    private static Integer SUCCESS_CODE = 200;
    private static String SUCCESS_MESSAGE = "success";
    private static Integer ERROR_CODE = -1;
    private static String ERROR_MESSAGE = "error";

    public static String success() {
        return JSON.toJSONString(new ApiResult(SUCCESS_CODE, SUCCESS_MESSAGE, new Object()));
    }

    public static String success(Object data) {
        return JSON.toJSONString(new ApiResult(SUCCESS_CODE, SUCCESS_MESSAGE, data));
    }

    public static String error(Object data) {
        return JSON.toJSONString(new ApiResult(ERROR_CODE, ERROR_MESSAGE, data));
    }

}
