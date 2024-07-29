package com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户注册请求体
 *

 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
