package com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.command;

import cn.hutool.core.collection.CollUtil;
import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.exception.ApiException;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class BulkOperationCommand<T> {

    public BulkOperationCommand(List<T> idList) {
        if (CollUtil.isEmpty(idList)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        // 移除重复元素
        this.ids = new HashSet<>(idList);
    }

    private Set<T> ids;

}