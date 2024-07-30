package com.xiaou.xiaoueasyprojectbackend.module.support.dept.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.command.AddDeptCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.command.UpdateDeptCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.entity.SysDeptEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.service.SysDeptService;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.enums.StatusEnum;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.util.BasicEnumUtil;

import java.util.Objects;

/**
 * @author valarchie
 */
public class DeptModel extends SysDeptEntity {

    private final SysDeptService deptService;

    public DeptModel(SysDeptService deptService) {
        this.deptService = deptService;
    }

    public DeptModel(SysDeptEntity entity, SysDeptService deptService) {
        if (entity != null) {
            // 如果大数据量的话  可以用MapStruct优化
            BeanUtil.copyProperties(entity, this);
        }
        this.deptService = deptService;
    }

    public void loadAddCommand(AddDeptCommand addCommand) {
        this.setParentId(addCommand.getParentId());
        this.setDeptName(addCommand.getDeptName());
        this.setOrderNum(addCommand.getOrderNum());
        this.setLeaderName(addCommand.getLeaderName());
        this.setPhone(addCommand.getPhone());
        this.setEmail(addCommand.getEmail());
        this.setStatus(addCommand.getStatus());
    }

    public void loadUpdateCommand(UpdateDeptCommand updateCommand) {
        loadAddCommand(updateCommand);
        setStatus(Convert.toInt(updateCommand.getStatus(), 0));
    }

    public void checkDeptNameUnique() {
        if (deptService.isDeptNameDuplicated(getDeptName(), getDeptId(), getParentId())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }

    public void checkParentIdConflict() {
        if (Objects.equals(getParentId(), getDeptId())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }

    public void checkHasChildDept() {
        if (deptService.hasChildrenDept(getDeptId(), null)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }



    public void generateAncestors() {
        if (getParentId() == 0) {
            setAncestors(getParentId().toString());
            return;
        }

        SysDeptEntity parentDept = deptService.getById(getParentId());

        if (parentDept == null || StatusEnum.DISABLE.equals(
            BasicEnumUtil.fromValue(StatusEnum.class, parentDept.getStatus()))) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        setAncestors(parentDept.getAncestors() + "," + getParentId());
    }


    /**
     * DDD 有些阻抗  如果为了追求性能的话  还是得通过 数据库的方式来判断
     */
    public void checkStatusAllowChange() {
        if (StatusEnum.DISABLE.getValue().equals(getStatus()) &&
            deptService.hasChildrenDept(getDeptId(), true)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

    }

}
