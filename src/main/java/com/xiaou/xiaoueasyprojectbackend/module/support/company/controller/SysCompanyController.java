package com.xiaou.xiaoueasyprojectbackend.module.support.company.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xiaou.xiaoueasyprojectbackend.module.support.company.entity.SysCompany;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.resp.RestResponse;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.search.SysCompanySearch;
import com.xiaou.xiaoueasyprojectbackend.module.support.company.service.SysCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门表 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Tag(name = "部门接口V2", description = "部门接口V2")

@RestController
@RequestMapping("/commons/sys-company")
@Slf4j
public class SysCompanyController {

    @Resource
    private SysCompanyService sysCompanyService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    public RestResponse<List<SysCompany>> list(SysCompanySearch search) {

        List<SysCompany> result = sysCompanyService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    public RestResponse<IPage<SysCompany>> listPage(@ModelAttribute SysCompanySearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysCompany> page = new Page<>(current, size);

        page = sysCompanyService.queryPage(page, search);

        log.info("support------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    public RestResponse<SysCompany> detail(@PathVariable("id") String id) {

        SysCompany result = sysCompanyService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    public RestResponse<SysCompany> add(@RequestBody SysCompany add) {

        sysCompanyService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    public RestResponse<SysCompany> update(
            @PathVariable("id") String id, @RequestBody SysCompany update) {

        update.setId(id);

        sysCompanyService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    public RestResponse<SysCompany> delete(@PathVariable("id") String id) {

        sysCompanyService.delete(id);

        return RestResponse.success();
    }
}