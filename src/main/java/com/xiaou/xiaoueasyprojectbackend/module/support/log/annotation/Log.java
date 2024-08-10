package com.xiaou.xiaoueasyprojectbackend.module.support.log.annotation;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 * @author wangchenghai
 * @date 2023/03/07 17:51:29
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 接口名称
     */
    public String title() default "";

}
