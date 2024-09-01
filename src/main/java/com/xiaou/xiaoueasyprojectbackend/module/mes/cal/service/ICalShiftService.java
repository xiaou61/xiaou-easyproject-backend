package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service;

import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalShift;

import java.util.List;


/**
 * 计划班次Service接口
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
public interface ICalShiftService 
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

    /**
     * 根据计划ID查询班次
     * @param planId
     * @return
     */
    public List<CalShift> selectCalShiftListByPlanId(Long planId);


    public int checkShiftCount(Long planId);


    public void addDefaultShift(Long plandId,String shiftType);

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
     * 批量删除计划班次
     * 
     * @param shiftIds 需要删除的计划班次主键集合
     * @return 结果
     */
    public int deleteCalShiftByShiftIds(Long[] shiftIds);

    /**
     * 删除计划班次信息
     * 
     * @param shiftId 计划班次主键
     * @return 结果
     */
    public int deleteCalShiftByShiftId(Long shiftId);

    public int deleteByPlanId(Long planId);

}
