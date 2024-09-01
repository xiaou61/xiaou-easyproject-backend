package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper;

import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalTeamMember;

import java.util.List;


/**
 * 班组成员Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-06-05
 */
public interface CalTeamMemberMapper 
{
    /**
     * 查询班组成员
     * 
     * @param memberId 班组成员主键
     * @return 班组成员
     */
    public CalTeamMember selectCalTeamMemberByMemberId(Long memberId);

    /**
     * 查询班组成员列表
     * 
     * @param calTeamMember 班组成员
     * @return 班组成员集合
     */
    public List<CalTeamMember> selectCalTeamMemberList(CalTeamMember calTeamMember);

    public CalTeamMember checkUserUnique(CalTeamMember calTeamMember);

    /**
     * 新增班组成员
     * 
     * @param calTeamMember 班组成员
     * @return 结果
     */
    public int insertCalTeamMember(CalTeamMember calTeamMember);

    /**
     * 修改班组成员
     * 
     * @param calTeamMember 班组成员
     * @return 结果
     */
    public int updateCalTeamMember(CalTeamMember calTeamMember);

    /**
     * 删除班组成员
     * 
     * @param memberId 班组成员主键
     * @return 结果
     */
    public int deleteCalTeamMemberByMemberId(Long memberId);

    public int deleteByTeamId(Long teamId);

    /**
     * 批量删除班组成员
     * 
     * @param memberIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCalTeamMemberByMemberIds(Long[] memberIds);
}
