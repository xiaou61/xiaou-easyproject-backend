package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.controller;

import cn.hutool.core.collection.CollUtil;

import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.common.utils.CalendarUtil;
import com.xiaou.xiaoueasyprojectbackend.common.utils.StringUtils;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants.UserConstants;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalCalendar;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalHoliday;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalCalendarService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalHolidayService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.support.BaseController;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaou61
 * @Date 2024/9/01 15:32
 * @Description 排班日历
 */
@RestController
@RequestMapping("/mes/cal/calendar")
public class CalCalendarController extends BaseController {

    @Resource
    private ICalCalendarService calCalendarService;

    @Resource
    private ICalHolidayService calHolidayService;

    @GetMapping("/list")
    public AjaxResult getCalendars(CalCalendar calCalendar){

        Date day = calCalendar.getDate();
        List<CalCalendar> days = null;
        if(StringUtils.isNull(day)){
            day = new Date();
        }

        if(UserConstants.CAL_QUERY_BY_TYPE.equals(calCalendar.getQueryType())){
            days=calCalendarService.getCalendarByType(day,calCalendar.getCalendarType());
        }else if(UserConstants.CAL_QUERY_BY_TEAM.equals(calCalendar.getQueryType())){
            days=calCalendarService.getCalendarByTeam(day,calCalendar.getTeamId());
        }else {
            days=calCalendarService.getCalendarByUser(day,calCalendar.getUserId());
        }
        return AjaxResult.success(getCalendarsWithoutHoliday(days));
    }


    /**
     * 过滤掉节假日
     * @param days
     * @return
     */
    private List<CalCalendar> getCalendarsWithoutHoliday(List<CalCalendar> days){
        CalHoliday param = new CalHoliday();
        List<CalHoliday> holidays = calHolidayService.selectCalHolidayList(param);
        if(CollUtil.isNotEmpty(holidays)){
            List<CalCalendar> results = days.stream().filter(
                    calCalendar -> holidays.stream().filter(calHoliday -> calCalendar.getTheDay().equals(CalendarUtil.getDateStr(calHoliday.getTheDay()))).collect(Collectors.toList()).size()==0
            ).collect(Collectors.toList());
            return results;
        }
        return days;
    }
}
