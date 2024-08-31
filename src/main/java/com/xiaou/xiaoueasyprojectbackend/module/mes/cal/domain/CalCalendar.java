package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain;



import com.xiaou.xiaoueasyprojectbackend.module.mes.support.BaseEntity;

import java.util.Date;
import java.util.List;

public class CalCalendar extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 查询参数部分
     */
    private String queryType;

    private Date date;

    private String calendarType;

    private Long teamId;

    private Long userId;


    /**
     * 返回值部分
     */
    private String theDay;

    private String shiftType;

    private List<CalTeamshift> teamShifts;


    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTheDay() {
        return theDay;
    }

    public void setTheDay(String theDay) {
        this.theDay = theDay;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public List<CalTeamshift> getTeamShifts() {
        return teamShifts;
    }

    public void setTeamShifts(List<CalTeamshift> teamShifts) {
        this.teamShifts = teamShifts;
    }

    @Override
    public String toString() {
        return "CalCalendar{" +
                "queryType='" + queryType + '\'' +
                ", date='" + date + '\'' +
                ", calendarType='" + calendarType + '\'' +
                ", teamId=" + teamId +
                ", userId=" + userId +
                ", theDay=" + theDay +
                ", shiftType='" + shiftType + '\'' +
                ", teamShifts=" + teamShifts +
                '}';
    }
}
