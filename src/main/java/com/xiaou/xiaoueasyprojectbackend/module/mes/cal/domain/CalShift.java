package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain;


import com.alibaba.excel.annotation.ExcelProperty;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 计划班次对象 cal_shift
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
public class CalShift extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班次ID */
    private Long shiftId;

    /** 计划ID */
    @ExcelProperty(value = "计划ID")
    private Long planId;

    /** 序号 */
    @ExcelProperty(value = "序号")
    private Integer orderNum;

    /** 班次名称 */
    @ExcelProperty(value = "班次名称")
    private String shiftName;

    /** 开始时间 */
    @ExcelProperty(value = "开始时间")
    private String startTime;

    /** 结束时间 */

    @ExcelProperty(value = "结束时间")
    private String endTime;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setShiftId(Long shiftId) 
    {
        this.shiftId = shiftId;
    }

    public Long getShiftId() 
    {
        return shiftId;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setShiftName(String shiftName) 
    {
        this.shiftName = shiftName;
    }

    public String getShiftName() 
    {
        return shiftName;
    }
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getStartTime()
    {
        return startTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getEndTime()
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
            .append("shiftId", getShiftId())
            .append("planId", getPlanId())
            .append("orderNum", getOrderNum())
            .append("shiftName", getShiftName())
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
