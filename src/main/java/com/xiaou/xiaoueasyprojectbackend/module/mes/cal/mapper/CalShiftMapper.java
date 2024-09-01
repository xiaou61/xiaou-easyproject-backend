package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper;

import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalShift;

import java.util.List;


/**
 * 计划班次Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
public interface CalShiftMapper 
{
    /**
     * 查询计划班次
     * 
     * @param shiftId 计划班次主键
     * @return 计划班次
     */
    public CalShift selectCalShiftByShiftId(Long shiftId);

    /**
     * 查询计划班次列表
     * 
     * @param calShift 计划班次
     * @return 计划班次集合
     */
    public List<CalShift> selectCalShiftList(CalShift calShift);




    public int checkShiftCount(Long planId);

    /**
     * 新增计划班次
     * 
     * @param calShift 计划班次
     * @return 结果
     */
    public int insertCalShift(CalShift calShift);

    /**
     * 修改计划班次
     * 
     * @param calShift 计划班次
     * @return 结果
     */
    public int updateCalShift(CalShift calShift);

    /**
     * 删除计划班次
     * 
     * @param shiftId 计划班次主键
     * @return 结果
     */
    public int deleteCalShiftByShiftId(Long shiftId);

    /**
     * 批量删除计划班次
     * 
     * @param shiftIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCalShiftByShiftIds(Long[] shiftIds);

    public int deleteByPlanId(Long planId);
}
