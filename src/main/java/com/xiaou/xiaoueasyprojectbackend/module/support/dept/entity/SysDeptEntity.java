package com.xiaou.xiaoueasyprojectbackend.module.support.dept.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev2.entity.BaseEntity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author valarchie
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_dept")

public class SysDeptEntity extends BaseEntity<SysDeptEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    @TableField("parent_id")
    private Long parentId;

    @TableField("ancestors")
    private String ancestors;

    @TableField("dept_name")
    private String deptName;


    @TableField("order_num")
    private Integer orderNum;

    @TableField("leader_id")
    private Long leaderId;


    @TableField("leader_name")
    private String leaderName;


    @TableField("phone")
    private String phone;


    @TableField("email")
    private String email;


    @TableField("`status`")
    private Integer status;


    @Override
    public Serializable pkVal() {
        return this.deptId;
    }

}
