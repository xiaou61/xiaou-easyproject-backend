package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.controller;

import cn.hutool.core.collection.CollectionUtil;

import com.alibaba.excel.EasyExcel;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.TableDataInfo;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.constants.UserConstants;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalHoliday;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlan;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalPlanTeam;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalPlanService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalPlanTeamService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalShiftService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalTeamshiftService;
import com.xiaou.xiaoueasyprojectbackend.module.mes.support.BaseController;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * 排班计划Controller
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
@RestController
@RequestMapping("/mes/cal/calplan")
public class CalPlanController extends BaseController
{
    @Autowired
    private ICalPlanService calPlanService;

    @Autowired
    private ICalShiftService calShiftService;

    @Autowired
    private ICalPlanTeamService calPlanTeamService;

    @Resource
    private ICalTeamshiftService calTeamshiftService;

    /**
     * 查询排班计划列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CalPlan calPlan)
    {
        List<CalPlan> list = calPlanService.selectCalPlanList(calPlan);
        return getDataTable(list);
    }

    /**
     * 导出排班计划列表
     */
    @SneakyThrows
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalPlan calPlan)
    {
        List<CalPlan> list = calPlanService.selectCalPlanList(calPlan);

        //这里是用easyexcel导出 下载路径可以自己修改
        FileOutputStream outputStream = new FileOutputStream(new File("D://排班计划数据.xlsx"));
        EasyExcel.write(outputStream, CalPlan.class).sheet("排班计划数据").doWrite(list);
    }

    /**
     * 获取排班计划详细信息
     */
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") Long planId)
    {
        return AjaxResult.success(calPlanService.selectCalPlanByPlanId(planId));
    }

    /**
     * 新增排班计划
     */
    @Transactional
    @PostMapping
    public AjaxResult add(@RequestBody CalPlan calPlan)
    {
        int ret = calPlanService.insertCalPlan(calPlan);
        //根据选择的轮班方式生成默认的班次
        calShiftService.addDefaultShift(calPlan.getPlanId(),calPlan.getShiftType());
        return toAjax(ret);
    }


    /**
     * 修改排班计划
     */
    @Transactional
    @PutMapping
    public AjaxResult edit(@RequestBody CalPlan calPlan)
    {
        if(UserConstants.ORDER_STATUS_CONFIRMED.equals(calPlan.getStatus())){

            //检查班组配置
            List<CalPlanTeam> teams = calPlanTeamService.selectCalPlanTeamListByPlanId(calPlan.getPlanId());
            if(CollectionUtil.isEmpty(teams)){
                return AjaxResult.error("请配置班组!");
            } else if(teams.size() != 2 && UserConstants.CAL_SHIFT_TYPE_TWO.equals(calPlan.getShiftType())){
                return AjaxResult.error("两班倒请配置两个班组!");
            } else if(teams.size() !=3 && UserConstants.CAL_SHIFT_TYPE_THREE.equals(calPlan.getShiftType())){
                return AjaxResult.error("三倒请配置三个班组!");
            }

            calTeamshiftService.genRecords(calPlan.getPlanId());
        }
        return toAjax(calPlanService.updateCalPlan(calPlan));
    }

    /**
     * 删除排班计划
     */
    @Transactional
	@DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable Long[] planIds)
    {
        for (Long planId:planIds
             ) {
            //状态判断
            CalPlan plan = calPlanService.selectCalPlanByPlanId(planId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(plan.getStatus())){
                return AjaxResult.error("只能删除草稿状态单据！");
            }
            calShiftService.deleteByPlanId(planId);
            calPlanTeamService.deleteByPlanId(planId);
        }
        return toAjax(calPlanService.deleteCalPlanByPlanIds(planIds));
    }
}
