package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.impl;

import java.util.List;


import com.xiaou.xiaoueasyprojectbackend.common.utils.StringUtils;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants.UserConstants;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalTeamMember;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper.CalTeamMemberMapper;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalTeamMemberService;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.util.DateUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 班组成员Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-05
 */
@Service
public class CalTeamMemberServiceImpl implements ICalTeamMemberService
{
    @Resource
    private CalTeamMemberMapper calTeamMemberMapper;

    /**
     * 查询班组成员
     * 
     * @param memberId 班组成员主键
     * @return 班组成员
     */
    @Override
    public CalTeamMember selectCalTeamMemberByMemberId(Long memberId)
    {
        return calTeamMemberMapper.selectCalTeamMemberByMemberId(memberId);
    }

    /**
     * 查询班组成员列表
     * 
     * @param calTeamMember 班组成员
     * @return 班组成员
     */
    @Override
    public List<CalTeamMember> selectCalTeamMemberList(CalTeamMember calTeamMember)
    {
        return calTeamMemberMapper.selectCalTeamMemberList(calTeamMember);
    }

    @Override
    public String checkUserUnique(CalTeamMember calTeamMember) {
        CalTeamMember member = calTeamMemberMapper.checkUserUnique(calTeamMember);
        Long memberId = calTeamMember.getMemberId()==null?-1L:calTeamMember.getMemberId();
        if(StringUtils.isNotNull(member) && memberId !=member.getMemberId()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增班组成员
     * 
     * @param calTeamMember 班组成员
     * @return 结果
     */
    @Override
    public int insertCalTeamMember(CalTeamMember calTeamMember)
    {
        calTeamMember.setCreateTime(DateUtils.getNowDate());
        return calTeamMemberMapper.insertCalTeamMember(calTeamMember);
    }

    /**
     * 修改班组成员
     * 
     * @param calTeamMember 班组成员
     * @return 结果
     */
    @Override
    public int updateCalTeamMember(CalTeamMember calTeamMember)
    {
        calTeamMember.setUpdateTime(DateUtils.getNowDate());
        return calTeamMemberMapper.updateCalTeamMember(calTeamMember);
    }

    /**
     * 批量删除班组成员
     * 
     * @param memberIds 需要删除的班组成员主键
     * @return 结果
     */
    @Override
    public int deleteCalTeamMemberByMemberIds(Long[] memberIds)
    {
        return calTeamMemberMapper.deleteCalTeamMemberByMemberIds(memberIds);
    }

    @Override
    public int deleteByTeamId(Long teamId) {
        return calTeamMemberMapper.deleteByTeamId(teamId);
    }

    /**
     * 删除班组成员信息
     * 
     * @param memberId 班组成员主键
     * @return 结果
     */
    @Override
    public int deleteCalTeamMemberByMemberId(Long memberId)
    {
        return calTeamMemberMapper.deleteCalTeamMemberByMemberId(memberId);
    }
}
