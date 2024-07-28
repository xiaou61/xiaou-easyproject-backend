package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.biz;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {

    private Long id;

    private String name;

    private Integer age;

}