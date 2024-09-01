package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.controller;


import com.alibaba.excel.EasyExcel;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.TableDataInfo;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalHoliday;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalTeam;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalTeamMemberService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalTeamService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.support.BaseController;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/8/31 15:25
 * @Description 班组接口
 */
@RestController
@RequestMapping("/mes/cal/team")
public class CalTeamController extends BaseController
{
    @Resource
    private ICalTeamService calTeamService;

    @Resource
    private ICalTeamMemberService calTeamMemberService;

    /**
     * 查询班组列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CalTeam calTeam)
    {
        List<CalTeam> list = calTeamService.selectCalTeamList(calTeam);
        return getDataTable(list);
    }

    /**
     * 查询所有班组列表
     */
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        CalTeam  calTeam= new CalTeam();
        List<CalTeam> list = calTeamService.selectCalTeamList(calTeam);
        return AjaxResult.success(list);
    }

    /**
     * 导出班组列表
     */
    @SneakyThrows
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalTeam calTeam)
    {
        List<CalTeam> list = calTeamService.selectCalTeamList(calTeam);

        //这里是用easyexcel导出 下载路径可以自己修改
        FileOutputStream outputStream = new FileOutputStream(new File("D://班组数据.xlsx"));
        EasyExcel.write(outputStream, CalTeam.class).sheet("班组数据").doWrite(list);
    }

    /**
     * 获取班组详细信息
     */
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") Long teamId)
    {
        return AjaxResult.success(calTeamService.selectCalTeamByTeamId(teamId));
    }

    /**
     * 新增班组
     */
    @PostMapping
    public AjaxResult add(@RequestBody CalTeam calTeam)
    {
        return toAjax(calTeamService.insertCalTeam(calTeam));
    }

    /**
     * 修改班组
     */
    @PutMapping
    public AjaxResult edit(@RequestBody CalTeam calTeam)
    {
        return toAjax(calTeamService.updateCalTeam(calTeam));
    }

    /**
     * 删除班组
     */
    @Transactional
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable Long[] teamIds)
    {
        for (Long teamId:teamIds
             ) {
            calTeamMemberService.deleteByTeamId(teamId);
        }
        return toAjax(calTeamService.deleteCalTeamByTeamIds(teamIds));
    }
}
