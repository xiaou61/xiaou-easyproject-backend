package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper;

import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlanTeam;

import java.util.List;


/**
 * 计划班组Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-06-07
 */
public interface CalPlanTeamMapper 
{
    /**
     * 查询计划班组
     * 
     * @param recordId 计划班组主键
     * @return 计划班组
     */
    public CalPlanTeam selectCalPlanTeamByRecordId(Long recordId);

    /**
     * 查询计划班组列表
     * 
     * @param calPlanTeam 计划班组
     * @return 计划班组集合
     */
    public List<CalPlanTeam> selectCalPlanTeamList(CalPlanTeam calPlanTeam);

    public CalPlanTeam checkPlanTeamUnique(CalPlanTeam calPlanTeam);

    /**
     * 新增计划班组
     * 
     * @param calPlanTeam 计划班组
     * @return 结果
     */
    public int insertCalPlanTeam(CalPlanTeam calPlanTeam);

    /**
     * 修改计划班组
     * 
     * @param calPlanTeam 计划班组
     * @return 结果
     */
    public int updateCalPlanTeam(CalPlanTeam calPlanTeam);

    /**
     * 删除计划班组
     * 
     * @param recordId 计划班组主键
     * @return 结果
     */
    public int deleteCalPlanTeamByRecordId(Long recordId);

    /**
     * 批量删除计划班组
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCalPlanTeamByRecordIds(Long[] recordIds);

    public int deleteByPlanId(Long planId);
}
