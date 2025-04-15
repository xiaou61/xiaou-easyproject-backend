package com.xiaou.web.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_user")
@Data
public class User {
    @TableId
    @TableLogic
    private Long id;
    private String name;
    private String password;
}
