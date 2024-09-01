package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.controller;


import com.alibaba.excel.EasyExcel;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.TableDataInfo;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants.UserConstants;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalHoliday;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlanTeam;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalPlanTeamService;
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
 * @Date 2024/9/01 15:41
 * @Description 计划班组controller
 */
@RestController
@RequestMapping("/mes/cal/planteam")
public class CalPlanTeamController extends BaseController
{
    @Resource
    private ICalPlanTeamService calPlanTeamService;

    /**
     * 查询计划班组列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CalPlanTeam calPlanTeam)
    {
        List<CalPlanTeam> list = calPlanTeamService.selectCalPlanTeamList(calPlanTeam);
        return getDataTable(list);
    }

    /**
     * 导出计划班组列表
     */
    @SneakyThrows
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalPlanTeam calPlanTeam)
    {
        List<CalPlanTeam> list = calPlanTeamService.selectCalPlanTeamList(calPlanTeam);

        //这里是用easyexcel导出 下载路径可以自己修改
        FileOutputStream outputStream = new FileOutputStream(new File("D://计划班组数据.xlsx"));
        EasyExcel.write(outputStream, CalPlanTeam.class).sheet("计划班组数据").doWrite(list);
    }

    /**
     * 获取计划班组详细信息
     */

    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(calPlanTeamService.selectCalPlanTeamByRecordId(recordId));
    }

    /**
     * 新增计划班组
     */

    @PostMapping
    public AjaxResult add(@RequestBody CalPlanTeam calPlanTeam)
    {
        if (UserConstants.NOT_UNIQUE.equals(calPlanTeamService.checkPlanTeamUnique(calPlanTeam))) {
            return AjaxResult.error("班组已添加，不能重复添加！");
        }
        return toAjax(calPlanTeamService.insertCalPlanTeam(calPlanTeam));
    }

    /**
     * 修改计划班组
     */

    @PutMapping
    public AjaxResult edit(@RequestBody CalPlanTeam calPlanTeam)
    {
        return toAjax(calPlanTeamService.updateCalPlanTeam(calPlanTeam));
    }

    /**
     * 删除计划班组
     */

	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(calPlanTeamService.deleteCalPlanTeamByRecordIds(recordIds));
    }
}
