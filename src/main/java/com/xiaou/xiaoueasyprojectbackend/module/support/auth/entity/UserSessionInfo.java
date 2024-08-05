package com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 用户全局会话对象
 * @Author: sxgan
 * @Date: 2024/3/1 15:15
 * @Version: 1.0
 **/
@Data
@Schema(name = "UserSessionInfo", description = "用户全局会话对象")
public class UserSessionInfo implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "用户ID", type = "Integer", example = "000000")
    private Long id;
    @Schema(description = "密码", type = "String", example = "123456")
    private String password;
    @Pattern(regexp = "^\\S{1,10}$")
    @Schema(description = "用户昵称", type = "String", example = "小张")
    private String userName;
    @NotEmpty
    @Email
    @Schema(description = "邮箱", type = "String", example = "123@123.com")
    private String email;
    @Schema(description = "验证码", type = "String", example = "666666")
    private String verifyCode;
    @Schema(description = "token", type = "String", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
            ".eyJpZCI6IjE3NzY5MTQzMzYxMjY4ODU4ODkiLCJlbWFpbCI6InN4Z2FuQGZveG1haWwuY29tIn0" +
            ".MeOvzfpSdaHyk2ldSFUSoOz_te5Uibx4v4fT89XSkXE")
    private String token;
}