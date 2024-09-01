package com.xiaou.xiaoueasyprojectbackend.module.mes.cal.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.md.domain.HrmRank;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.domain.CalHoliday;
import com.xiaou.xiaoueasyprojectbackend.module.mes.cal.service.ICalHolidayService;
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
 * @Date 2024/8/31 14:55
 * @Description 节假日设置接口
 */
@RestController
@RequestMapping("/mes/cal/calholiday")
public class CalHolidayController extends BaseController
{
    @Resource
    private ICalHolidayService calHolidayService;

    /**
     * 查询节假日设置列表
     */
    @GetMapping("/list")
    public AjaxResult list(CalHoliday calHoliday)
    {
        List<CalHoliday> list = calHolidayService.selectCalHolidayList(calHoliday);
        return AjaxResult.success(list);
    }

    /**
     * 导出节假日设置列表
     */
    @SneakyThrows
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalHoliday calHoliday)
    {
        List<CalHoliday> list = calHolidayService.selectCalHolidayList(calHoliday);
        //这里是用easyexcel导出 下载路径可以自己修改
        FileOutputStream outputStream = new FileOutputStream(new File("D://节假日设置数据.xlsx"));
        EasyExcel.write(outputStream, CalHoliday.class).sheet("节假日设置数据").doWrite(list);

    }

    /**
     * 获取节假日设置详细信息
     */
    @GetMapping(value = "/{holidayId}")
    public AjaxResult getInfo(@PathVariable("holidayId") Long holidayId)
    {
        return AjaxResult.success(calHolidayService.selectCalHolidayByHolidayId(holidayId));
    }

    /**
     * 新增节假日设置
     */
    @PostMapping
    public AjaxResult add(@RequestBody CalHoliday calHoliday)
    {
        CalHoliday param = new CalHoliday();
        param.setTheDay(calHoliday.getTheDay());
        List<CalHoliday> days = calHolidayService.selectCalHolidayList(param);
        if(CollUtil.isNotEmpty(days)){
            calHoliday.setHolidayId(days.get(0).getHolidayId());
            return toAjax(calHolidayService.updateCalHoliday(calHoliday));
        }else{
            return toAjax(calHolidayService.insertCalHoliday(calHoliday));
        }
    }

    /**
     * 修改节假日设置
     */
    @PutMapping
    public AjaxResult edit(@RequestBody CalHoliday calHoliday)
    {
        return toAjax(calHolidayService.updateCalHoliday(calHoliday));
    }

    /**
     * 删除节假日设置
     */
	@DeleteMapping("/{holidayIds}")
    public AjaxResult remove(@PathVariable Long[] holidayIds)
    {
        return toAjax(calHolidayService.deleteCalHolidayByHolidayIds(holidayIds));
    }
}
