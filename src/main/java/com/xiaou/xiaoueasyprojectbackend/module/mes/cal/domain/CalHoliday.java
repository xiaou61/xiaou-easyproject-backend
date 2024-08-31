package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.xiaou.xiaoueasyprojectbackend.module.mes.support.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 节假日设置对象 cal_holiday
 * 
 * @author yinjinlu
 * @date 2022-06-08
 */
public class CalHoliday extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long holidayId;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "日期")
    private Date theDay;

    /** 日期类型 */
    @ExcelProperty(value = "日期类型")
    private String holidayType;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "开始时间")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "结束时间")
    private Date endTime;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setHolidayId(Long holidayId) 
    {
        this.holidayId = holidayId;
    }

    public Long getHolidayId() 
    {
        return holidayId;
    }
    public void setTheDay(Date theDay) 
    {
        this.theDay = theDay;
    }

    public Date getTheDay() 
    {
        return theDay;
    }
    public void setHolidayType(String holidayType) 
    {
        this.holidayType = holidayType;
    }

    public String getHolidayType() 
    {
        return holidayType;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setAttr1(String attr1) 
    {
        this.attr1 = attr1;
    }

    public String getAttr1() 
    {
        return attr1;
    }
    public void setAttr2(String attr2) 
    {
        this.attr2 = attr2;
    }

    public String getAttr2() 
    {
        return attr2;
    }
    public void setAttr3(Long attr3) 
    {
        this.attr3 = attr3;
    }

    public Long getAttr3() 
    {
        return attr3;
    }
    public void setAttr4(Long attr4) 
    {
        this.attr4 = attr4;
    }

    public Long getAttr4() 
    {
        return attr4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("holidayId", getHolidayId())
            .append("theDay", getTheDay())
            .append("holidayType", getHolidayType())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("remark", getRemark())
            .append("attr1", getAttr1())
            .append("attr2", getAttr2())
            .append("attr3", getAttr3())
            .append("attr4", getAttr4())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
