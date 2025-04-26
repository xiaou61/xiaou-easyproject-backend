package com.xiaou.exam.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("u_exam_repo")
public class Repo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private Integer userId;

    @NotBlank(message = "题库名不能为空")
    private String title;

    /**
     * 是否可以刷题
     */
    private Integer isExercise;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableLogic
    private Integer isDeleted;

    private Integer categoryId;
}
