package com.xiaou.xiaoueasyprojectbackend.module.support.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description: 用户信息视图对象
 * @Author: sxgan
 * @Date: 2024-04-10 10:39
 * @Version: 1.0
 **/
@Data
@Schema(name = "SysUserVO", description = "用户信息视图对象")
public class SysUserVO {
    @Schema(description = "用户ID", type = "Long")
    private Long userId;
    @Schema(description = "用户昵称", type = "String")
    private String userName;
    @Schema(description = "用户邮箱", type = "String")
    private String email;
    @Schema(description = "用户性别（0男 1女 2未知）", type = "Integer")
    private Integer sex;
    @Schema(description = "手机号码", type = "String")
    private String phoneNumber;
    @Schema(description = "头像路径", type = "String")
    private String avatar;
    @Schema(description = "个性签名", type = "String")
    private String personalSign;
    @Schema(description = "部门ID", type = "Long")
    private Long deptId;
    @Schema(description = "用户类型（0系统用户 1普通用户）", type = "Integer")
    private Integer userType;
    @Schema(description = "帐号状态（0正常 1停用）", type = "Integer")
    private Integer status;
    @Schema(description = "备注", type = "String")
    private String remark;
    @Schema(description = "删除标志（0代表存在 1代表删除）", type = "Integer")
    private Integer delFlag;
}
