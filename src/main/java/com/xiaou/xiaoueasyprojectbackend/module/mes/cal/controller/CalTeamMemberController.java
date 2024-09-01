package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.controller;



import com.alibaba.excel.EasyExcel;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.md.domain.HrmRank;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.TableDataInfo;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants.UserConstants;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalTeamMember;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalTeamMemberService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.support.BaseController;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
/**
 * @author xiaou61
 * @Date 2024/9/01 15:25
 * @Description 班组成员Controller
 */
@RestController
@RequestMapping("/mes/cal/teammember")
public class CalTeamMemberController extends BaseController
{
    @Resource
    private ICalTeamMemberService calTeamMemberService;

    /**
     * 查询班组成员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CalTeamMember calTeamMember)
    {
        List<CalTeamMember> list = calTeamMemberService.selectCalTeamMemberList(calTeamMember);
        return getDataTable(list);
    }

    /**
     * 导出班组成员列表
     */

    @SneakyThrows
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalTeamMember calTeamMember)
    {
        List<CalTeamMember> list = calTeamMemberService.selectCalTeamMemberList(calTeamMember);
        //这里是用easyexcel导出 下载路径可以自己修改
        FileOutputStream outputStream = new FileOutputStream(new File("D://班组成员数据.xlsx"));
        EasyExcel.write(outputStream, CalTeamMember.class).sheet("班组成员数据").doWrite(list);
    }

    /**
     * 获取班组成员详细信息
     */

    @GetMapping(value = "/{memberId}")
    public AjaxResult getInfo(@PathVariable("memberId") Long memberId)
    {
        return AjaxResult.success(calTeamMemberService.selectCalTeamMemberByMemberId(memberId));
    }

    /**
     * 新增班组成员
     */

    @PostMapping
    public AjaxResult add(@RequestBody CalTeamMember calTeamMember)
    {
        if(UserConstants.NOT_UNIQUE.equals(calTeamMemberService.checkUserUnique(calTeamMember))){
            return AjaxResult.error("用户"+calTeamMember.getNickName()+"已分配过班组！");
        }

        return toAjax(calTeamMemberService.insertCalTeamMember(calTeamMember));
    }

    /**
     * 删除班组成员
     */

	@DeleteMapping("/{memberIds}")
    public AjaxResult remove(@PathVariable Long[] memberIds)
    {
        return toAjax(calTeamMemberService.deleteCalTeamMemberByMemberIds(memberIds));
    }
}
