package com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xiaou.xiaoueasyprojectbackend.module.support.company.resp.RestResponse;
import com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.entity.SysMenu;
import com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.search.SysMenuSearch;
import com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Tag(name = "菜单管理V2", description = "菜单管理V2")

@RestController
@RequestMapping("/commons/sys-menu")
@Slf4j
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    public RestResponse<List<SysMenu>> list(SysMenuSearch search) {

        List<SysMenu> result = sysMenuService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    public RestResponse<IPage<SysMenu>> listPage(@ModelAttribute SysMenuSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysMenu> page = new Page<>(current, size);

        page = sysMenuService.queryPage(page, search);

        log.info("support------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    public RestResponse<SysMenu> detail(@PathVariable("id") String id) {

        SysMenu result = sysMenuService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    public RestResponse<SysMenu> add(@RequestBody SysMenu add) {

        sysMenuService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    public RestResponse<SysMenu> update(
            @PathVariable("id") String id, @RequestBody SysMenu update) {

        update.setId(id);

        sysMenuService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    public RestResponse<SysMenu> delete(@PathVariable("id") String id) {

        sysMenuService.delete(id);

        return RestResponse.success();
    }
}