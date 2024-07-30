package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.util;

import cn.hutool.core.convert.Convert;
import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.enums.BasicEnum;


import java.util.Objects;

/**
 * @author valarchie
 */
public class BasicEnumUtil {

    private BasicEnumUtil() {
    }

    public static final String UNKNOWN = "未知";

    public static <E extends Enum<E>> E fromValueSafely(Class<E> enumClass, Object value) {
        E target = null;

        for (E enumConstant : enumClass.getEnumConstants()) {
            BasicEnum<?> basicEnum = (BasicEnum<?>) enumConstant;
            if (Objects.equals(basicEnum.getValue(), value)) {
                target = (E) basicEnum;
            }
        }

        return target;
    }

    public static <E extends Enum<E>> E fromValue(Class<E> enumClass, Object value) {
        E target = null;

        for (E enumConstant : enumClass.getEnumConstants()) {
            BasicEnum basicEnum = (BasicEnum) enumConstant;
            if (Objects.equals(basicEnum.getValue(), value)) {
                target = (E) basicEnum;
            }
        }

        if (target == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return target;
    }

    public static <E extends Enum<E>> String getDescriptionByBool(Class<E> enumClass, Boolean bool) {
        Integer value = Convert.toInt(bool, 0);
        return getDescriptionByValue(enumClass, value);
    }

    public static <E extends Enum<E>> String getDescriptionByValue(Class<E> enumClass, Object value) {
        E basicEnum = fromValueSafely(enumClass, value);
        if (basicEnum != null) {
            return ((BasicEnum<?>) basicEnum).description();
        }
        return UNKNOWN;
    }

}
