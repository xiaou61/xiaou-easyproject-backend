package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.enums;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dictionary {

    /**
     * 字典类型名称
     */
    String name() default "";


}
