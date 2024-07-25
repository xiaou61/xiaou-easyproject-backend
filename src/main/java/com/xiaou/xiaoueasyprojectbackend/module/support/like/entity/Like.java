package com.xiaou.xiaoueasyprojectbackend.module.support.like.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName like
 */
@TableName(value ="`like`")
@Data
@Builder
public class Like implements Serializable {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer productId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}