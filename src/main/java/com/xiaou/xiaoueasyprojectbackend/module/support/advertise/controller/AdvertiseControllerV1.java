package com.xiaou.xiaoueasyprojectbackend.module.support.advertise.controller;

import com.xiaou.xiaoueasyprojectbackend.module.support.advertise.model.CommonPage;
import com.xiaou.xiaoueasyprojectbackend.module.support.advertise.model.SmsHomeAdvertise;
import com.xiaou.xiaoueasyprojectbackend.module.support.advertise.service.SmsHomeAdvertiseService;
import com.xiaou.xiaoueasyprojectbackend.module.support.upload.result.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/7/28 11:42
 * @Description: 广告管理
 */
@Tag(name = "广告管理V1", description = "广告管理")
@RestController
@RequestMapping("/v1/advertise")
public class AdvertiseControllerV1 {

    @Resource
    private SmsHomeAdvertiseService advertiseService;

    /**
     * {
     *   "id": 2,
     *   "name": "夏日大促销测试",
     *   "type": 0,
     *   "pic": "http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190525/ad1.jpg",
     *   "startTime": "2024-07-28 00:00:00",
     *   "endTime": "2024-07-29 00:00:00",
     *   "status": 1,
     *   "clickCount": 0,
     *   "orderCount": 0,
     *   "url": "www.baidu.com",
     *   "note": "测试",
     *   "sort": 0
     * }
     * @param advertise
     * @return
     */
    @Operation(description = "添加广告")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.create(advertise);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @Operation(description = "删除广告")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = advertiseService.delete(ids);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @Operation(description = "修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        int count = advertiseService.updateStatus(id, status);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @Operation(description = "获取广告详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsHomeAdvertise> getItem(@PathVariable Long id) {
        SmsHomeAdvertise advertise = advertiseService.getItem(id);
        return CommonResult.success(advertise);
    }

    @Operation(description = "修改广告")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.update(id, advertise);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @Operation(description = "分页查询广告")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeAdvertise>> list(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "type", required = false) Integer type,
                                                           @RequestParam(value = "endTime", required = false) String endTime,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeAdvertise> advertiseList = advertiseService.list(name, type, endTime, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(advertiseList));
    }
}
