package com.xiaou.xiaoueasyprojectbackend.module.support.like.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName product
 */
@TableName(value ="product")
@Data
@Builder
public class Product implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer likeCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}