package com.xiaou.xiaoueasyprojectbackend.module.hrm.md.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 职级管理对象 hrm_rank
 *
 * @author t3rik
 * @date 2024-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "hrm_rank")
public class HrmRank extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 职级主键id
     */
    @TableId
    private Long rankId;


    /**
     * 职级编码
     */
    @ExcelProperty(value = "职级编码")
    private String rankCode;

    /**
     * 职级层次
     */
    @ExcelProperty(value = "职级层次")
    private String rankType;

    /**
     * 职级层次名称
     */
    @ExcelProperty(value = "职级层次名称")
    private String rankName;


    /**
     * 薪资范围下限
     */
    @ExcelProperty(value = "薪资范围下限")
    private BigDecimal salaryRangeMin;


    /**
     * 薪资范围上限
     */
    @ExcelProperty(value = "薪资范围上限")
    private BigDecimal salaryRangeMax;


    /**
     * 逻辑删除字段 0:未删除 1:已删除
     */
    @ExcelProperty(value = "逻辑删除字段 0:未删除 1:已删除")
    @TableLogic
    private Long deleted;


    /**
     * 逻辑删除辅助字段
     */
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "逻辑删除辅助字段")
    @TableField("deleteAt")
    private Date deleteAt;


    /**
     * 乐观锁
     */
    @ExcelProperty(value = "乐观锁")
    @Version
    private Long version;


}
