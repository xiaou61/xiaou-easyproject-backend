package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.collection.CollUtil;

import com.xiaou.xiaoueasyprojectbackend.common.utils.CalendarUtil;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants.UserConstants;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlan;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlanTeam;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalShift;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalTeamshift;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper.CalPlanMapper;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper.CalPlanTeamMapper;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper.CalShiftMapper;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper.CalTeamshiftMapper;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalTeamshiftService;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.util.DateUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * 班组排班Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-11
 */
@Service
public class CalTeamshiftServiceImpl implements ICalTeamshiftService
{
    @Resource
    private CalTeamshiftMapper calTeamshiftMapper;

    @Resource
    private CalPlanMapper calPlanMapper;

    @Resource
    private CalShiftMapper calShiftMapper;

    @Resource
    private CalPlanTeamMapper calPlanTeamMapper;

    /**
     * 查询班组排班
     * 
     * @param recordId 班组排班主键
     * @return 班组排班
     */
    @Override
    public CalTeamshift selectCalTeamshiftByRecordId(Long recordId)
    {
        return calTeamshiftMapper.selectCalTeamshiftByRecordId(recordId);
    }

    /**
     * 查询班组排班列表
     * 
     * @param calTeamshift 班组排班
     * @return 班组排班
     */
    @Override
    public List<CalTeamshift> selectCalTeamshiftList(CalTeamshift calTeamshift)
    {
        return calTeamshiftMapper.selectCalTeamshiftList(calTeamshift);
    }

    /**
     * 新增班组排班
     * 
     * @param calTeamshift 班组排班
     * @return 结果
     */
    @Override
    public int insertCalTeamshift(CalTeamshift calTeamshift)
    {
        calTeamshift.setCreateTime(DateUtils.getNowDate());
        return calTeamshiftMapper.insertCalTeamshift(calTeamshift);
    }

    /**
     * 修改班组排班
     * 
     * @param calTeamshift 班组排班
     * @return 结果
     */
    @Override
    public int updateCalTeamshift(CalTeamshift calTeamshift)
    {
        calTeamshift.setUpdateTime(DateUtils.getNowDate());
        return calTeamshiftMapper.updateCalTeamshift(calTeamshift);
    }

    /**
     * 批量删除班组排班
     * 
     * @param recordIds 需要删除的班组排班主键
     * @return 结果
     */
    @Override
    public int deleteCalTeamshiftByRecordIds(Long[] recordIds)
    {
        return calTeamshiftMapper.deleteCalTeamshiftByRecordIds(recordIds);
    }

    /**
     * 删除班组排班信息
     * 
     * @param recordId 班组排班主键
     * @return 结果
     */
    @Override
    public int deleteCalTeamshiftByRecordId(Long recordId)
    {
        return calTeamshiftMapper.deleteCalTeamshiftByRecordId(recordId);
    }

    /**
     * 根据排班计划生成每个班组的明细排班记录
     * 1.查询计划头
     * 2.查询计划中的班组
     * 3.查询计划中的班次
     * 4.计算计划的开始日期和结束日期的差值
     * 5.遍历每一天，设置每一天的班组与班次的对应关系（要结合轮班方式）
     * @param plandId
     */
    @Async
    @Override
    public void genRecords(Long plandId) {
        CalPlan plan =calPlanMapper.selectCalPlanByPlanId(plandId);
        CalShift p1 = new CalShift();
        p1.setPlanId(plandId);
        List<CalShift> shifts = calShiftMapper.selectCalShiftList(p1);
        CalPlanTeam p2 = new CalPlanTeam();
        p2.setPlanId(plandId);
        List<CalPlanTeam> teams = calPlanTeamMapper.selectCalPlanTeamList(p2);
        Long days = CalendarUtil.getDateDiff(plan.getStartDate(),plan.getEndDate());

        int shiftIndex =0;
        Date nowDate =null;
        for(int i =0;i<days;i++){
            //当前遍历到的日期
            nowDate = CalendarUtil.addDays(plan.getStartDate(),i);

            //按季度轮班
            if(UserConstants.CAL_SHIFT_METHOD_QUARTER.equals(plan.getShiftMethod())){
                //获取季度第一天
                String quarterStart = CalendarUtil.getQuarterStartStr(nowDate);

                //如果到了季度第一天，并且不是排班计划的开始季度
                if(quarterStart.equals(CalendarUtil.getDateStr(nowDate)) && !quarterStart.equals(CalendarUtil.getQuarterStartStr(plan.getStartDate()))){
                    shiftIndex ++; //轮班一次
                }
            }
            //按月轮班
            else if(UserConstants.CAL_SHIFT_METHOD_MONTH.equals(plan.getShiftMethod())){
                //获取月份第一天
                String monthStart = CalendarUtil.getMonthStartStr(nowDate);

                //如果到了月初，并且不是排班计划开始月份
                if(monthStart.equals(CalendarUtil.getDateStr(nowDate)) && !monthStart.equals(CalendarUtil.getMonthStartStr(plan.getStartDate()))){
                    shiftIndex ++; //轮班一次
                }
            }
            //按周轮班
            else if(UserConstants.CAL_SHIFT_METHOD_WEEK.equals(plan.getShiftMethod())){
                //获取周的第一天
                String weekStart = CalendarUtil.getWeekStartTimeStr(nowDate);

                //如果是每周一，并且不是排班计划开始周
                if(weekStart.equals(CalendarUtil.getDateStr(nowDate)) && !weekStart.equals(CalendarUtil.getWeekStartTimeStr(plan.getStartDate()))){
                    shiftIndex ++;
                }
            }
            //按天
            else{
                //如果到了指定的轮班天数，并且不是刚开始
                if(i%(plan.getShiftCount())==0 && i!=0){
                    shiftIndex ++;
                }
            }

            //如果是单白班，不需要倒班
            if(UserConstants.CAL_SHIFT_TYPE_SINGLE.equals(plan.getShiftType())){
                CalTeamshift bean = new CalTeamshift();
                bean.setPlanId(plandId);
                bean.setCalendarType(plan.getCalendarType());
                bean.setShiftType(plan.getShiftType());
                bean.setTheDay(CalendarUtil.getDateStr(nowDate));
                bean.setTeamId(teams.get(0).getTeamId());
                bean.setTeamName(teams.get(0).getTeamName());
                bean.setShiftId(shifts.get(0).getShiftId());
                bean.setShiftName(shifts.get(0).getShiftName());
                saveTeamShift(bean);
            }

            //如果是两班倒
            if(UserConstants.CAL_SHIFT_TYPE_TWO.equals(plan.getShiftType())){
                if(shiftIndex%2 == 0){
                    //A组上白班
                    CalTeamshift beanA = new CalTeamshift();
                    beanA.setPlanId(plandId);
                    beanA.setCalendarType(plan.getCalendarType());
                    beanA.setShiftType(plan.getShiftType());
                    beanA.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanA.setTeamId(teams.get(0).getTeamId());
                    beanA.setTeamName(teams.get(0).getTeamName());
                    beanA.setShiftId(shifts.get(0).getShiftId());
                    beanA.setShiftName(shifts.get(0).getShiftName());
                    beanA.setOrderNum(1L);
                    saveTeamShift(beanA);
                    //B组上夜班
                    CalTeamshift beanB = new CalTeamshift();
                    beanB.setPlanId(plandId);
                    beanB.setCalendarType(plan.getCalendarType());
                    beanB.setShiftType(plan.getShiftType());
                    beanB.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanB.setTeamId(teams.get(1).getTeamId());
                    beanB.setTeamName(teams.get(1).getTeamName());
                    beanB.setShiftId(shifts.get(1).getShiftId());
                    beanB.setShiftName(shifts.get(1).getShiftName());
                    beanB.setOrderNum(2L);
                    saveTeamShift(beanB);
                }else{
                    //A组上夜班
                    CalTeamshift beanA = new CalTeamshift();
                    beanA.setPlanId(plandId);
                    beanA.setCalendarType(plan.getCalendarType());
                    beanA.setShiftType(plan.getShiftType());
                    beanA.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanA.setTeamId(teams.get(0).getTeamId());
                    beanA.setTeamName(teams.get(0).getTeamName());
                    beanA.setShiftId(shifts.get(1).getShiftId());
                    beanA.setShiftName(shifts.get(1).getShiftName());
                    beanA.setOrderNum(2L);
                    saveTeamShift(beanA);
                    //B组上白班
                    CalTeamshift beanB = new CalTeamshift();
                    beanB.setPlanId(plandId);
                    beanB.setCalendarType(plan.getCalendarType());
                    beanB.setShiftType(plan.getShiftType());
                    beanB.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanB.setTeamId(teams.get(1).getTeamId());
                    beanB.setTeamName(teams.get(1).getTeamName());
                    beanB.setShiftId(shifts.get(0).getShiftId());
                    beanB.setShiftName(shifts.get(0).getShiftName());
                    beanB.setOrderNum(1L);
                    saveTeamShift(beanB);
                }
            }

            //如果是三班倒
            if(UserConstants.CAL_SHIFT_TYPE_THREE.equals(plan.getShiftType())){
                if(shiftIndex%2 == 0){
                    //A组上白班
                    CalTeamshift beanA = new CalTeamshift();
                    beanA.setPlanId(plandId);
                    beanA.setCalendarType(plan.getCalendarType());
                    beanA.setShiftType(plan.getShiftType());
                    beanA.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanA.setTeamId(teams.get(0).getTeamId());
                    beanA.setTeamName(teams.get(0).getTeamName());
                    beanA.setShiftId(shifts.get(0).getShiftId());
                    beanA.setShiftName(shifts.get(0).getShiftName());
                    beanA.setOrderNum(1L);
                    saveTeamShift(beanA);
                    //B组上中班
                    CalTeamshift beanB = new CalTeamshift();
                    beanB.setPlanId(plandId);
                    beanB.setCalendarType(plan.getCalendarType());
                    beanB.setShiftType(plan.getShiftType());
                    beanB.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanB.setTeamId(teams.get(1).getTeamId());
                    beanB.setTeamName(teams.get(1).getTeamName());
                    beanB.setShiftId(shifts.get(1).getShiftId());
                    beanB.setShiftName(shifts.get(1).getShiftName());
                    beanB.setOrderNum(2L);
                    saveTeamShift(beanB);
                    //C组上夜班
                    CalTeamshift beanC = new CalTeamshift();
                    beanC.setPlanId(plandId);
                    beanC.setCalendarType(plan.getCalendarType());
                    beanC.setShiftType(plan.getShiftType());
                    beanC.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanC.setTeamId(teams.get(2).getTeamId());
                    beanC.setTeamName(teams.get(2).getTeamName());
                    beanC.setShiftId(shifts.get(2).getShiftId());
                    beanC.setShiftName(shifts.get(2).getShiftName());
                    beanC.setOrderNum(3L);
                    saveTeamShift(beanC);
                }else if(shiftIndex%2 == 1){
                    //A组上中班
                    CalTeamshift beanA = new CalTeamshift();
                    beanA.setPlanId(plandId);
                    beanA.setCalendarType(plan.getCalendarType());
                    beanA.setShiftType(plan.getShiftType());
                    beanA.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanA.setTeamId(teams.get(0).getTeamId());
                    beanA.setTeamName(teams.get(0).getTeamName());
                    beanA.setShiftId(shifts.get(1).getShiftId());
                    beanA.setShiftName(shifts.get(1).getShiftName());
                    beanA.setOrderNum(2L);
                    saveTeamShift(beanA);
                    //B组上夜班
                    CalTeamshift beanB = new CalTeamshift();
                    beanB.setPlanId(plandId);
                    beanB.setCalendarType(plan.getCalendarType());
                    beanB.setShiftType(plan.getShiftType());
                    beanB.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanB.setTeamId(teams.get(1).getTeamId());
                    beanB.setTeamName(teams.get(1).getTeamName());
                    beanB.setShiftId(shifts.get(2).getShiftId());
                    beanB.setShiftName(shifts.get(2).getShiftName());
                    beanB.setOrderNum(3L);
                    saveTeamShift(beanB);
                    //C组上白班
                    CalTeamshift beanC = new CalTeamshift();
                    beanC.setPlanId(plandId);
                    beanC.setCalendarType(plan.getCalendarType());
                    beanC.setShiftType(plan.getShiftType());
                    beanC.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanC.setTeamId(teams.get(2).getTeamId());
                    beanC.setTeamName(teams.get(2).getTeamName());
                    beanC.setShiftId(shifts.get(0).getShiftId());
                    beanC.setShiftName(shifts.get(0).getShiftName());
                    beanC.setOrderNum(1L);
                    saveTeamShift(beanC);
                }else{
                    //A组上夜班
                    CalTeamshift beanA = new CalTeamshift();
                    beanA.setPlanId(plandId);
                    beanA.setCalendarType(plan.getCalendarType());
                    beanA.setShiftType(plan.getShiftType());
                    beanA.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanA.setTeamId(teams.get(0).getTeamId());
                    beanA.setTeamName(teams.get(0).getTeamName());
                    beanA.setShiftId(shifts.get(2).getShiftId());
                    beanA.setShiftName(shifts.get(2).getShiftName());
                    beanA.setOrderNum(3L);
                    saveTeamShift(beanA);
                    //B组上白班
                    CalTeamshift beanB = new CalTeamshift();
                    beanB.setPlanId(plandId);
                    beanB.setCalendarType(plan.getCalendarType());
                    beanB.setShiftType(plan.getShiftType());
                    beanB.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanB.setTeamId(teams.get(1).getTeamId());
                    beanB.setTeamName(teams.get(1).getTeamName());
                    beanB.setShiftId(shifts.get(0).getShiftId());
                    beanB.setShiftName(shifts.get(0).getShiftName());
                    beanB.setOrderNum(1L);
                    saveTeamShift(beanB);
                    //C组上中班
                    CalTeamshift beanC = new CalTeamshift();
                    beanC.setPlanId(plandId);
                    beanC.setCalendarType(plan.getCalendarType());
                    beanC.setShiftType(plan.getShiftType());
                    beanC.setTheDay(CalendarUtil.getDateStr(nowDate));
                    beanC.setTeamId(teams.get(2).getTeamId());
                    beanC.setTeamName(teams.get(2).getTeamName());
                    beanC.setShiftId(shifts.get(1).getShiftId());
                    beanC.setShiftName(shifts.get(1).getShiftName());
                    beanC.setOrderNum(2L);
                    saveTeamShift(beanC);
                }
            }
        }
    }

    private void saveTeamShift(CalTeamshift calTeamshift){
        CalTeamshift param = new CalTeamshift();
        param.setTheDay(calTeamshift.getTheDay());
        param.setTeamId(calTeamshift.getTeamId());
        List<CalTeamshift> teamshifts = calTeamshiftMapper.selectCalTeamshiftList(param);
        if(CollUtil.isNotEmpty(teamshifts)){
            calTeamshift.setRecordId(teamshifts.get(0).getRecordId());
            calTeamshiftMapper.updateCalTeamshift(calTeamshift);
        }else{
            calTeamshiftMapper.insertCalTeamshift(calTeamshift);
        }
    }

}
