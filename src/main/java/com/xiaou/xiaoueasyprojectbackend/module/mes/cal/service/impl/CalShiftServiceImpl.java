package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.impl;

import java.util.List;


import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants.UserConstants;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalShift;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper.CalShiftMapper;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalShiftService;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.util.DateUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 计划班次Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
@Service
public class CalShiftServiceImpl implements ICalShiftService
{
    @Resource
    private CalShiftMapper calShiftMapper;

    /**
     * 查询计划班次
     * 
     * @param shiftId 计划班次主键
     * @return 计划班次
     */
    @Override
    public CalShift selectCalShiftByShiftId(Long shiftId)
    {
        return calShiftMapper.selectCalShiftByShiftId(shiftId);
    }

    /**
     * 查询计划班次列表
     * 
     * @param calShift 计划班次
     * @return 计划班次
     */
    @Override
    public List<CalShift> selectCalShiftList(CalShift calShift)
    {
        return calShiftMapper.selectCalShiftList(calShift);
    }

    @Override
    public List<CalShift> selectCalShiftListByPlanId(Long planId) {
        CalShift param = new CalShift();
        param.setPlanId(planId);
        return calShiftMapper.selectCalShiftList(param);
    }

    @Override
    public int checkShiftCount(Long planId) {
        return calShiftMapper.checkShiftCount(planId);
    }

    @Override
    public void addDefaultShift(Long plandId,String shiftType) {
        if(UserConstants.CAL_SHIFT_TYPE_SINGLE.equals(shiftType)){
            CalShift shift = new CalShift();
            shift.setPlanId(plandId);
            shift.setShiftName(UserConstants.CAL_SHIFT_NAME_DAY);
            shift.setOrderNum(1);
            shift.setStartTime("8:00");
            shift.setEndTime("18:00");
            calShiftMapper.insertCalShift(shift);
        }else if(UserConstants.CAL_SHIFT_TYPE_TWO.equals(shiftType)){
            CalShift shiftDay = new CalShift();
            shiftDay.setPlanId(plandId);
            shiftDay.setShiftName(UserConstants.CAL_SHIFT_NAME_DAY);
            shiftDay.setOrderNum(1);
            shiftDay.setStartTime("8:00");
            shiftDay.setEndTime("20:00");
            CalShift shiftNight = new CalShift();
            shiftNight.setPlanId(plandId);
            shiftNight.setShiftName(UserConstants.CAL_SHIFT_NAME_NIGHT);
            shiftNight.setOrderNum(2);
            shiftNight.setStartTime("20:00");
            shiftNight.setEndTime("8:00");
            calShiftMapper.insertCalShift(shiftDay);
            calShiftMapper.insertCalShift(shiftNight);
        }else {
            CalShift shiftDay = new CalShift();
            shiftDay.setPlanId(plandId);
            shiftDay.setShiftName(UserConstants.CAL_SHIFT_NAME_DAY);
            shiftDay.setOrderNum(1);
            shiftDay.setStartTime("8:00");
            shiftDay.setEndTime("16:00");
            CalShift shiftMid = new CalShift();
            shiftMid.setPlanId(plandId);
            shiftMid.setShiftName(UserConstants.CAL_SHIFT_NAME_MID);
            shiftMid.setOrderNum(2);
            shiftMid.setStartTime("16:00");
            shiftMid.setEndTime("24:00");
            CalShift shiftNight = new CalShift();
            shiftNight.setPlanId(plandId);
            shiftNight.setShiftName(UserConstants.CAL_SHIFT_NAME_NIGHT);
            shiftNight.setOrderNum(3);
            shiftNight.setStartTime("00:00");
            shiftNight.setEndTime("8:00");
            calShiftMapper.insertCalShift(shiftDay);
            calShiftMapper.insertCalShift(shiftMid);
            calShiftMapper.insertCalShift(shiftNight);
        }
    }

    /**
     * 新增计划班次
     * 
     * @param calShift 计划班次
     * @return 结果
     */
    @Override
    public int insertCalShift(CalShift calShift)
    {
        calShift.setCreateTime(DateUtils.getNowDate());
        return calShiftMapper.insertCalShift(calShift);
    }

    /**
     * 修改计划班次
     * 
     * @param calShift 计划班次
     * @return 结果
     */
    @Override
    public int updateCalShift(CalShift calShift)
    {
        calShift.setUpdateTime(DateUtils.getNowDate());
        return calShiftMapper.updateCalShift(calShift);
    }

    /**
     * 批量删除计划班次
     * 
     * @param shiftIds 需要删除的计划班次主键
     * @return 结果
     */
    @Override
    public int deleteCalShiftByShiftIds(Long[] shiftIds)
    {
        return calShiftMapper.deleteCalShiftByShiftIds(shiftIds);
    }

    /**
     * 删除计划班次信息
     * 
     * @param shiftId 计划班次主键
     * @return 结果
     */
    @Override
    public int deleteCalShiftByShiftId(Long shiftId)
    {
        return calShiftMapper.deleteCalShiftByShiftId(shiftId);
    }

    @Override
    public int deleteByPlanId(Long planId) {
        return calShiftMapper.deleteByPlanId(planId);
    }
}
