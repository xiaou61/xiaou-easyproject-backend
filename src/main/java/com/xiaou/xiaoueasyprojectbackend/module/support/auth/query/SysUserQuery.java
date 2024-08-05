package com.xiaou.xiaoueasyprojectbackend.module.support.auth.query;

import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserQuery extends SysUser {
    private Date createStartDate;
    private Date createEndDate;
    private Date updateStartDate;
    private Date updateEndDate;
}