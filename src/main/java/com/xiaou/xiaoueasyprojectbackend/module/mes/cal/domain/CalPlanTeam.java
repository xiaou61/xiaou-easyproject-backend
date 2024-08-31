package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain;


import com.alibaba.excel.annotation.ExcelProperty;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 计划班组对象 cal_plan_team
 * 
 * @author yinjinlu
 * @date 2022-06-07
 */
public class CalPlanTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long recordId;

    /** 计划ID */
    @ExcelProperty(value = "计划ID")
    private Long planId;

    /** 班组ID */
    @ExcelProperty(value = "班组ID")
    private Long teamId;

    /** 班组编号 */
    @ExcelProperty(value = "班组编号")
    private String teamCode;

    /** 班组名称 */
    @ExcelProperty(value = "班组名称")
    private String teamName;

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
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }
    public void setTeamCode(String teamCode) 
    {
        this.teamCode = teamCode;
    }

    public String getTeamCode() 
    {
        return teamCode;
    }
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
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
            .append("recordId", getRecordId())
            .append("planId", getPlanId())
            .append("teamId", getTeamId())
            .append("teamCode", getTeamCode())
            .append("teamName", getTeamName())
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
