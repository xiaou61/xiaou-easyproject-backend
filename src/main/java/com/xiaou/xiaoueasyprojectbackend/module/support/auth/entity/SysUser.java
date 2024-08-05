package com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "sys_user")
@Schema(name = "SysUser", description = "用户实体类")
public class SysUser {
    // 指定主键使用数据库ID自增策略
    @TableId(type = IdType.AUTO)
    @Schema(description = "用户ID", type = "Long")
    private Long userId;
    @Schema(description = "用户昵称", type = "String")
    private String userName;
    @Schema(description = "用户邮箱", type = "String")
    private String email;
    @Schema(description = "密码", type = "String")
    private String password;
    @Schema(description = "用户性别（0男 1女 2未知）", type = "Integer")
    private Integer sex;
    @Schema(description = "手机号码", type = "String")
    private String phoneNumber;
    @Schema(description = "头像路径", type = "String")
    private String avatar;
    @Schema(description = "个性签名", type = "String")
    private String personalSign;
    @Schema(description = "盐加密", type = "String")
    private String salt;
    @Schema(description = "部门ID", type = "Long")
    private Long deptId;
    @Schema(description = "用户类型（0系统用户 1普通用户）", type = "Integer")
    private Integer userType;
    @Schema(description = "帐号状态（0正常 1停用）", type = "Integer")
    private Integer status;
    @Schema(description = "最后登录IP", type = "String")
    private String loginIp;
    @Schema(description = "最后登录时间", type = "Date")
    private Date loginDate;
    @Schema(description = "密码最后更新时间", type = "Date")
    private Date pwdUpdateDate;
    @Schema(description = "备注", type = "String")
    private String remark;
    @Schema(description = "创建者", type = "String")
    private String createBy;
    @Schema(description = "更新者", type = "String")
    private String updateBy;
    @Schema(description = "创建时间", type = "Date")
    private Date createTime;
    @Schema(description = "更新时间", type = "Date")
    private Date updateTime;
    @Schema(description = "删除标志（0代表存在 1代表删除）", type = "Integer")
    private Integer delFlag;
}