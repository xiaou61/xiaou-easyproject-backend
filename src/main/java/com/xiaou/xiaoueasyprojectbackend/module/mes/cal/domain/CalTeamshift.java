package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain;


import com.alibaba.excel.annotation.ExcelProperty;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.BaseEntity;

/**
 * 班组排班对象 cal_teamshift
 * 
 * @author yinjinlu
 * @date 2022-06-11
 */
public class CalTeamshift extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long recordId;

    /** 日期 */
    private String theDay;

    /** 班组ID */
    @ExcelProperty(value = "班组ID")
    private Long teamId;

    /** 班组名称 */
    @ExcelProperty(value = "班组名称")
    private String teamName;

    /** 班次ID */
    @ExcelProperty(value = "班次ID")
    private Long shiftId;

    /** 班次名称 */
    @ExcelProperty(value = "班次名称")
    private String shiftName;

    /** 序号 */
    @ExcelProperty(value = "序号")
    private Long orderNum;

    /** 计划ID */
    @ExcelProperty(value = "计划ID")
    private Long planId;

    private String calendarType;

    /** 轮班方式 */
    @ExcelProperty(value = "轮班方式")
    private String shiftType;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setTheDay(String theDay)
    {
        this.theDay = theDay;
    }

    public String getTheDay()
    {
        return theDay;
    }
    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
    }
    public void setShiftId(Long shiftId) 
    {
        this.shiftId = shiftId;
    }

    public Long getShiftId() 
    {
        return shiftId;
    }
    public void setShiftName(String shiftName) 
    {
        this.shiftName = shiftName;
    }

    public String getShiftName() 
    {
        return shiftName;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setShiftType(String shiftType) 
    {
        this.shiftType = shiftType;
    }

    public String getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    public String getShiftType()
    {
        return shiftType;
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
        return "CalTeamshift{" +
                "recordId=" + recordId +
                ", theDay=" + theDay +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", shiftId=" + shiftId +
                ", shiftName='" + shiftName + '\'' +
                ", orderNum=" + orderNum +
                ", planId=" + planId +
                ", calendarType='" + calendarType + '\'' +
                ", shiftType='" + shiftType + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
