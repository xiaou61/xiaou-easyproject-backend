package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service;

import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalTeam;

import java.util.List;


/**
 * 班组Service接口
 * 
 * @author yinjinlu
 * @date 2022-06-05
 */
public interface ICalTeamService 
{
    /**
     * 查询班组
     * 
     * @param teamId 班组主键
     * @return 班组
     */
    public CalTeam selectCalTeamByTeamId(Long teamId);

    /**
     * 查询班组列表
     * 
     * @param calTeam 班组
     * @return 班组集合
     */
    public List<CalTeam> selectCalTeamList(CalTeam calTeam);

    /**
     * 新增班组
     * 
     * @param calTeam 班组
     * @return 结果
     */
    public int insertCalTeam(CalTeam calTeam);

    /**
     * 修改班组
     * 
     * @param calTeam 班组
     * @return 结果
     */
    public int updateCalTeam(CalTeam calTeam);

    /**
     * 批量删除班组
     * 
     * @param teamIds 需要删除的班组主键集合
     * @return 结果
     */
    public int deleteCalTeamByTeamIds(Long[] teamIds);

    /**
     * 删除班组信息
     * 
     * @param teamId 班组主键
     * @return 结果
     */
    public int deleteCalTeamByTeamId(Long teamId);
}
