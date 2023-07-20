package com.xiaou.xiaoueasyprojectbackend.module.support.question.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("t_option")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Option implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id   选项答案表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 试题id
     */
    private Integer quId;

    /**
     * 是否正确
     */
    @NotNull(message = "选型是否正确(isRight)不能为空")
    @Min(value = 0, message = "选项是否正确(isRight)只能是：0错误1正确")
    @Max(value = 1, message = "选项是否正确(isRight)只能是：0错误1正确")
    private Integer isRight;

    /**
     * 图片地址   0错误 1正确
     */
    private String image;

    /**
     * 选项内容
     */
    @NotBlank(message = "选型内容(content)不能为空")
    private String content;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 逻辑删除字段
     */
    private Integer isDeleted;
}
