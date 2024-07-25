package com.xiaou.xiaoueasyprojectbackend.common.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToEmail implements Serializable {

   //邮件接受方

    private String tos;

   //邮件主题

    private String subject;


   //邮件内容

    private String content;
}
