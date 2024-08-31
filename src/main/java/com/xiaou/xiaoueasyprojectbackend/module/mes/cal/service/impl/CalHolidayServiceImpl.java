package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.impl;


import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalHoliday;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper.CalHolidayMapper;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalHolidayService;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.util.DateUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 节假日设置Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-08
 */
@Service
public class CalHolidayServiceImpl implements ICalHolidayService
{


    @Autowired
    private CalHolidayMapper calHolidayMapper;

    /**
     * 查询节假日设置
     * 
     * @param holidayId 节假日设置主键
     * @return 节假日设置
     */
    @Override
    public CalHoliday selectCalHolidayByHolidayId(Long holidayId)
    {
        return calHolidayMapper.selectCalHolidayByHolidayId(holidayId);
    }

    /**
     * 查询节假日设置列表
     * 
     * @param calHoliday 节假日设置
     * @return 节假日设置
     */
    @Override
    public List<CalHoliday> selectCalHolidayList(CalHoliday calHoliday)
    {
        return calHolidayMapper.selectCalHolidayList(calHoliday);
    }

    /**
     * 新增节假日设置
     * 
     * @param calHoliday 节假日设置
     * @return 结果
     */
    @Override
    public int insertCalHoliday(CalHoliday calHoliday)
    {
        calHoliday.setCreateTime(DateUtils.getNowDate());
        return calHolidayMapper.insertCalHoliday(calHoliday);
    }

    /**
     * 修改节假日设置
     * 
     * @param calHoliday 节假日设置
     * @return 结果
     */
    @Override
    public int updateCalHoliday(CalHoliday calHoliday)
    {
        calHoliday.setUpdateTime(DateUtils.getNowDate());
        return calHolidayMapper.updateCalHoliday(calHoliday);
    }

    /**
     * 批量删除节假日设置
     * 
     * @param holidayIds 需要删除的节假日设置主键
     * @return 结果
     */
    @Override
    public int deleteCalHolidayByHolidayIds(Long[] holidayIds)
    {
        return calHolidayMapper.deleteCalHolidayByHolidayIds(holidayIds);
    }

    /**
     * 删除节假日设置信息
     * 
     * @param holidayId 节假日设置主键
     * @return 结果
     */
    @Override
    public int deleteCalHolidayByHolidayId(Long holidayId)
    {
        return calHolidayMapper.deleteCalHolidayByHolidayId(holidayId);
    }
}
