package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.controller;


import com.alibaba.excel.EasyExcel;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.TableDataInfo;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants.UserConstants;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlan;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlanTeam;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalShift;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalPlanService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalShiftService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.support.BaseController;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/9/01 15:45
 * @Description 计划班次controller
 */
@RestController
@RequestMapping("/mes/cal/shift")
public class CalShiftController extends BaseController
{
    @Resource
    private ICalShiftService calShiftService;

    @Resource
    private ICalPlanService calPlanService;

    /**
     * 查询计划班次列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CalShift calShift)
    {
        List<CalShift> list = calShiftService.selectCalShiftList(calShift);
        return getDataTable(list);
    }

    /**
     * 导出计划班次列表
     */
    @SneakyThrows
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalShift calShift)
    {
        List<CalShift> list = calShiftService.selectCalShiftList(calShift);
        //这里是用easyexcel导出 下载路径可以自己修改
        FileOutputStream outputStream = new FileOutputStream(new File("D://计划班次数据.xlsx"));
        EasyExcel.write(outputStream, CalShift.class).sheet("计划班次数据").doWrite(list);
    }

    /**
     * 获取计划班次详细信息
     */
    public AjaxResult getInfo(@PathVariable("shiftId") Long shiftId)
    {
        return AjaxResult.success(calShiftService.selectCalShiftByShiftId(shiftId));
    }

    /**
     * 新增计划班次
     */
    @PostMapping
    public AjaxResult add(@RequestBody CalShift calShift)
    {
        int count = calShiftService.checkShiftCount(calShift.getPlanId());
        CalPlan plan = calPlanService.selectCalPlanByPlanId(calShift.getPlanId());
        if(UserConstants.CAL_SHIFT_TYPE_SINGLE.equals(plan.getShiftType())&&count>0){
            return AjaxResult.error("轮班方式为 白班 时只能有一个班次！");
        }
        if(UserConstants.CAL_SHIFT_TYPE_TWO.equals(plan.getShiftType())&&count>1){
            return AjaxResult.error("轮班方式为 两班倒 时只能有两个班次！");
        }
        if(UserConstants.CAL_SHIFT_TYPE_THREE.equals(plan.getShiftType())&&count>2){
            return AjaxResult.error("轮班方式为 三班倒 时只能有三个班次！");
        }

        return toAjax(calShiftService.insertCalShift(calShift));
    }

    /**
     * 修改计划班次
     */

    @PutMapping
    public AjaxResult edit(@RequestBody CalShift calShift)
    {
        return toAjax(calShiftService.updateCalShift(calShift));
    }

    /**
     * 删除计划班次
     */

	@DeleteMapping("/{shiftIds}")
    public AjaxResult remove(@PathVariable Long[] shiftIds)
    {
        return toAjax(calShiftService.deleteCalShiftByShiftIds(shiftIds));
    }
}
