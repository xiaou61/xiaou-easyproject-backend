package com.xiaou.exam.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 选项实体类
 *
 * @author WeiJin
 * @since 2024-03-21
 */
@Data
@TableName("u_exam_option")
public class Option implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    private Integer quId;


    @NotNull(message = "选型是否正确(isRight)不能为空")
    @Min(value = 0,message = "选项是否正确(isRight)只能是：0错误1正确")
    @Max(value = 1,message = "选项是否正确(isRight)只能是：0错误1正确")
    private Integer isRight;

    /**
     * 0错误 1正确
     */
    private String image;


    @NotBlank(message = "选型内容(content)不能为空")
    private String content;

    private Integer sort;

    @TableLogic
    private Integer isDeleted;
}
