package com.xiaou.xiaoueasyprojectbackend.module.support.question.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("t_exercise_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 题库id
     */
    private Integer repoId;

    /**
     * 试题id
     */
    private Integer questionId;

    /**
     * 用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer userId;

    /**
     * 主观题答案
     */
    private String answer;

    /**
     * 题目类型
     */
    private Integer questionType;

    /**
     * 客观题答案集合  用于客观题,多选题id使用","分隔
     */
    private String options;

    /**
     * 客观题是否正确
     */
    private Integer isRight;
}