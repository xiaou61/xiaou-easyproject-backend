package com.xiaou.xiaoueasyprojectbackend.module.support.dept.service;


import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.command.AddDeptCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.entity.SysDeptEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.model.DeptModel;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class DeptModelFactory {

    private final SysDeptService deptService;

    public DeptModel loadById(Long deptId) {
        SysDeptEntity byId = deptService.getById(deptId);
        if (byId == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return new DeptModel(byId, deptService);
    }

    public DeptModel create() {
        return new DeptModel(deptService);
    }

    public DeptModel loadFromAddCommand(AddDeptCommand addCommand, DeptModel model) {
        model.setParentId(addCommand.getParentId());
        model.setDeptName(addCommand.getDeptName());
        model.setOrderNum(addCommand.getOrderNum());
        model.setLeaderName(addCommand.getLeaderName());
        model.setPhone(addCommand.getPhone());
        model.setEmail(addCommand.getEmail());
        return model;
    }



}
