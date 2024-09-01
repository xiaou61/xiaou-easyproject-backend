package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service;

import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalCalendar;

import java.util.Date;
import java.util.List;

public interface ICalCalendarService {

    /**
     * 查询某个班组类型在某月的排班日历
     * @param day
     * @param calenderType
     * @return
     */
    List<CalCalendar> getCalendarByType(Date day, String calenderType);

    /**
     * 查询某个班组在某个月的排班日历
     * @param day
     * @param teamId
     * @return
     */
    List<CalCalendar> getCalendarByTeam(Date day, Long teamId);

    /**
     * 查询某个人在某月的排班日历
     * @param day
     * @param userId
     * @return
     */
    List<CalCalendar> getCalendarByUser(Date day,Long userId);
}
