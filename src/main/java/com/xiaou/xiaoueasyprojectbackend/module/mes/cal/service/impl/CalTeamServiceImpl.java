package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.impl;


import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalTeam;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper.CalTeamMapper;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalTeamService;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.util.DateUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 班组Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-05
 */
@Service
public class CalTeamServiceImpl implements ICalTeamService
{
    @Resource
    private CalTeamMapper calTeamMapper;

    /**
     * 查询班组
     * 
     * @param teamId 班组主键
     * @return 班组
     */
    @Override
    public CalTeam selectCalTeamByTeamId(Long teamId)
    {
        return calTeamMapper.selectCalTeamByTeamId(teamId);
    }

    /**
     * 查询班组列表
     * 
     * @param calTeam 班组
     * @return 班组
     */
    @Override
    public List<CalTeam> selectCalTeamList(CalTeam calTeam)
    {
        return calTeamMapper.selectCalTeamList(calTeam);
    }

    /**
     * 新增班组
     * 
     * @param calTeam 班组
     * @return 结果
     */
    @Override
    public int insertCalTeam(CalTeam calTeam)
    {
        calTeam.setCreateTime(DateUtils.getNowDate());
        return calTeamMapper.insertCalTeam(calTeam);
    }

    /**
     * 修改班组
     * 
     * @param calTeam 班组
     * @return 结果
     */
    @Override
    public int updateCalTeam(CalTeam calTeam)
    {
        calTeam.setUpdateTime(DateUtils.getNowDate());
        return calTeamMapper.updateCalTeam(calTeam);
    }

    /**
     * 批量删除班组
     * 
     * @param teamIds 需要删除的班组主键
     * @return 结果
     */
    @Override
    public int deleteCalTeamByTeamIds(Long[] teamIds)
    {
        return calTeamMapper.deleteCalTeamByTeamIds(teamIds);
    }

    /**
     * 删除班组信息
     * 
     * @param teamId 班组主键
     * @return 结果
     */
    @Override
    public int deleteCalTeamByTeamId(Long teamId)
    {
        return calTeamMapper.deleteCalTeamByTeamId(teamId);
    }
}
