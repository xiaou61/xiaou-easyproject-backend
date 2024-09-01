package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.mapper;

import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalTeamshift;

import java.util.List;


/**
 * 班组排班Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-06-11
 */
public interface CalTeamshiftMapper 
{
    /**
     * 查询班组排班
     * 
     * @param recordId 班组排班主键
     * @return 班组排班
     */
    public CalTeamshift selectCalTeamshiftByRecordId(Long recordId);

    /**
     * 查询班组排班列表
     * 
     * @param calTeamshift 班组排班
     * @return 班组排班集合
     */
    public List<CalTeamshift> selectCalTeamshiftList(CalTeamshift calTeamshift);



    /**
     * 新增班组排班
     * 
     * @param calTeamshift 班组排班
     * @return 结果
     */
    public int insertCalTeamshift(CalTeamshift calTeamshift);

    /**
     * 修改班组排班
     * 
     * @param calTeamshift 班组排班
     * @return 结果
     */
    public int updateCalTeamshift(CalTeamshift calTeamshift);

    /**
     * 删除班组排班
     * 
     * @param recordId 班组排班主键
     * @return 结果
     */
    public int deleteCalTeamshiftByRecordId(Long recordId);

    /**
     * 批量删除班组排班
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCalTeamshiftByRecordIds(Long[] recordIds);
}
