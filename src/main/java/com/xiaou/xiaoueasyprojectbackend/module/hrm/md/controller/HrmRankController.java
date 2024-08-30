package com.xiaou.xiaoueasyprojectbackend.module.hrm.md.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.common.utils.StringUtils;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.md.domain.HrmRank;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.md.service.IHrmRankService;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.BaseController;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.TableDataInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiaou61
 * @Date 2024/8/30 18:10
 * @Description 职级管理
 */
@RestController
@RequestMapping("/md/hrm-rank")
@Tag(name = "职级管理", description = "职级管理管理")
public class HrmRankController extends BaseController {
    @Resource
    private IHrmRankService hrmRankService;


    /**
     * 查询职级管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HrmRank hrmRank) {
        // 获取查询条件
        LambdaQueryWrapper<HrmRank> queryWrapper = getQueryWrapper(hrmRank);
        // 组装分页
        Page<HrmRank> page = getMPPage(hrmRank);
        // 查询
        this.hrmRankService.page(page, queryWrapper);
        return getDataTableWithPage(page);
    }

    /**
     * 导出职级管理列表
     */
    @SneakyThrows
    @PostMapping("/export")
    public void export(HrmRank hrmRank) {
        // 获取查询条件
        LambdaQueryWrapper<HrmRank> queryWrapper = getQueryWrapper(hrmRank);
        List<HrmRank> list = this.hrmRankService.list(queryWrapper);

        //这里是用easyexcel导出 下载路径可以自己修改
        FileOutputStream outputStream = new FileOutputStream(new File("D:/职级管理数据.xlsx"));
        EasyExcel.write(outputStream, HrmRank.class).sheet("职级管理数据").doWrite(list);

    }

    /**
     * 获取职级管理详细信息
     */
    @GetMapping(value = "/{rankId}")
    public AjaxResult getInfo(@PathVariable("rankId") Long rankId) {
        return AjaxResult.success(this.hrmRankService.getById(rankId));
    }

    /**
     * 新增职级管理
     */
    @PostMapping
    public AjaxResult add(@RequestBody HrmRank hrmRank) {
        return toAjax(this.hrmRankService.save(hrmRank));
    }

    /**
     * 修改职级管理
     */
    @PutMapping
    public AjaxResult edit(@RequestBody HrmRank hrmRank) {
        return toAjax(this.hrmRankService.updateById(hrmRank));
    }

    /**
     * 删除职级管理
     */
    @DeleteMapping("/{rankIds}")
    public AjaxResult remove(@PathVariable List<Long> rankIds) {
        return toAjax(this.hrmRankService.lambdaUpdate().in(HrmRank::getRankId, rankIds).set(HrmRank::getDeleted, Boolean.TRUE).set(HrmRank::getDeleteAt, new Date()).update());
    }

    /**
     * 获取查询条件
     */
    public LambdaQueryWrapper<HrmRank> getQueryWrapper(HrmRank hrmRank) {
        LambdaQueryWrapper<HrmRank> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(hrmRank.getRankCode() != null, HrmRank::getRankCode, hrmRank.getRankCode());
        queryWrapper.like(StringUtils.isNotEmpty(hrmRank.getRankType()), HrmRank::getRankType, hrmRank.getRankType());
        queryWrapper.like(StringUtils.isNotEmpty(hrmRank.getRankName()), HrmRank::getRankName, hrmRank.getRankName());
        queryWrapper.ge(hrmRank.getSalaryRangeMin() != null, HrmRank::getSalaryRangeMin, hrmRank.getSalaryRangeMin());
        queryWrapper.le(hrmRank.getSalaryRangeMax() != null, HrmRank::getSalaryRangeMax, hrmRank.getSalaryRangeMax());
        // 默认创建时间倒序
        queryWrapper.orderByAsc(HrmRank::getRankCode);
        Map<String, Object> params = hrmRank.getParams();
        queryWrapper.between(params.get("beginTime") != null && params.get("endTime") != null, HrmRank::getCreateTime, params.get("beginTime"), params.get("endTime"));
        return queryWrapper;
    }
}
