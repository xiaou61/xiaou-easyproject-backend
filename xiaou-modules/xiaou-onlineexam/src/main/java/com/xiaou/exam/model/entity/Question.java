package com.xiaou.exam.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("u_exam_question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer quType;

    private String image;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String analysis;

    private Integer repoId;

    @TableField(fill = FieldFill.INSERT)
    private Integer userId;

    @TableLogic
    private Integer isDeleted;
}
