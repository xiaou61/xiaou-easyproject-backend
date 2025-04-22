package com.xiaou.spring.beans.exception;

/**
 * 自定义异常类，用于处理 Bean 创建、获取等过程中的异常
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
