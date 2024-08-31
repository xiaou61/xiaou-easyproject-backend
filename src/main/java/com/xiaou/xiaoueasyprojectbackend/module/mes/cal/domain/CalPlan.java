package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaou.xiaoueasyprojectbackend.module.mes.support.BaseEntity;


import java.util.Date;

/**
 * 排班计划对象 cal_plan
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
public class CalPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    private Long planId;

    /** 计划编号 */
    @ExcelProperty(value = "计划编号")
    private String planCode;

    /** 计划名称 */
    @ExcelProperty(value = "计划名称")
    private String planName;

    private String calendarType;

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "开始日期")
    private Date startDate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "结束日期")
    private Date endDate;

    /** 轮班方式 */
    @ExcelProperty(value = "轮班方式")
    private String shiftType;

    /** 倒班方式 */
    @ExcelProperty(value = "倒班方式")
    private String shiftMethod;

    /** 数 */
    @ExcelProperty(value = "数")
    private Long shiftCount;

    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }
    public void setPlanCode(String planCode)
    {
        this.planCode = planCode;
    }

    public String getPlanCode()
    {
        return planCode;
    }
    public void setPlanName(String planName) 
    {
        this.planName = planName;
    }

    public String getPlanName() 
    {
        return planName;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public String getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    public Date getStartDate()
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setShiftType(String shiftType) 
    {
        this.shiftType = shiftType;
    }

    public String getShiftType() 
    {
        return shiftType;
    }
    public void setShiftMethod(String shiftMethod) 
    {
        this.shiftMethod = shiftMethod;
    }

    public String getShiftMethod() 
    {
        return shiftMethod;
    }
    public void setShiftCount(Long shiftCount) 
    {
        this.shiftCount = shiftCount;
    }

    public Long getShiftCount() 
    {
        return shiftCount;
    }
    public void setAttr1(String attr1) 
    {
        this.attr1 = attr1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "CalPlan{" +
                "planId=" + planId +
                ", planCode='" + planCode + '\'' +
                ", planName='" + planName + '\'' +
                ", calendarType='" + calendarType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", shiftType='" + shiftType + '\'' +
                ", shiftMethod='" + shiftMethod + '\'' +
                ", shiftCount=" + shiftCount +
                ", status='" + status + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
