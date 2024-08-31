package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain;


import com.alibaba.excel.annotation.ExcelProperty;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.BaseEntity;

/**
 * 班组对象 cal_team
 * 
 * @author yinjinlu
 * @date 2022-06-05
 */
public class CalTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班组ID */
    private Long teamId;

    /** 班组编号 */
    @ExcelProperty(value = "班组编号")
    private String teamCode;

    /** 班组名称 */
    @ExcelProperty(value = "班组名称")
    private String teamName;

    private String calendarType;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

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

    public String getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
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
        return "CalTeam{" +
                "teamId=" + teamId +
                ", teamCode='" + teamCode + '\'' +
                ", teamName='" + teamName + '\'' +
                ", calendarType='" + calendarType + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
