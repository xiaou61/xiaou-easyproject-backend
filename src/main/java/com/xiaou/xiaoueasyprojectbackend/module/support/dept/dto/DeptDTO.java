package com.xiaou.xiaoueasyprojectbackend.module.support.dept.dto;


import java.util.Date;

import com.xiaou.xiaoueasyprojectbackend.module.support.dept.entity.SysDeptEntity;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.enums.StatusEnum;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.util.BasicEnumUtil;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class DeptDTO {

    public DeptDTO(SysDeptEntity entity) {
        if (entity != null) {
            this.id = entity.getDeptId();
            this.parentId = entity.getParentId();
            this.deptName = entity.getDeptName();
            this.orderNum = entity.getOrderNum();
            this.leaderName = entity.getLeaderName();
            this.email = entity.getEmail();
            this.phone = entity.getPhone();
            this.status = entity.getStatus();
            this.createTime = entity.getCreateTime();
            this.statusStr = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());
        }
    }


    private Long id;

    private Long parentId;

    private String deptName;

    private Integer orderNum;

    private String leaderName;

    private String phone;

    private String email;

    private Integer status;

    private String statusStr;

    private Date createTime;

}
