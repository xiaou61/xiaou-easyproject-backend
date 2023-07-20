package com.xiaou.xiaoueasyprojectbackend.module.support.question.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("t_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id   试题表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 试题类型
     */
    private Integer quType;

    /**
     * 试题图片
     */
    private String image;

    /**
     * 题干
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 题目分析
     */
    private String analysis;

    /**
     * 题库id
     */
    private Integer repoId;
    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer userId;

    /**
     * 逻辑删除字段
     */
    private Integer isDeleted;
}