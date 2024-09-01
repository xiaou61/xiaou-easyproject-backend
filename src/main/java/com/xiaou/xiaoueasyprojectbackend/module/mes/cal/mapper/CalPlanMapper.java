package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper;

import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlan;

import java.util.List;


/**
 * 排班计划Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
public interface CalPlanMapper 
{
    /**
     * 查询排班计划
     * 
     * @param planId 排班计划主键
     * @return 排班计划
     */
    public CalPlan selectCalPlanByPlanId(Long planId);

    /**
     * 查询排班计划列表
     * 
     * @param calPlan 排班计划
     * @return 排班计划集合
     */
    public List<CalPlan> selectCalPlanList(CalPlan calPlan);





    /**
     * 新增排班计划
     * 
     * @param calPlan 排班计划
     * @return 结果
     */
    public int insertCalPlan(CalPlan calPlan);

    /**
     * 修改排班计划
     * 
     * @param calPlan 排班计划
     * @return 结果
     */
    public int updateCalPlan(CalPlan calPlan);

    /**
     * 删除排班计划
     * 
     * @param planId 排班计划主键
     * @return 结果
     */
    public int deleteCalPlanByPlanId(Long planId);

    /**
     * 批量删除排班计划
     * 
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCalPlanByPlanIds(Long[] planIds);
}
