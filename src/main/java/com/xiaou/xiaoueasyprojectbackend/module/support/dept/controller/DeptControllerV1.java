package com.xiaou.xiaoueasyprojectbackend.module.support.dept.controller;

import cn.hutool.core.lang.tree.Tree;

import com.xiaou.xiaoueasyprojectbackend.module.support.dept.command.AddDeptCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.command.UpdateDeptCommand;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.dto.DeptDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.query.DeptQuery;
import com.xiaou.xiaoueasyprojectbackend.module.support.dept.service.DeptApplicationService;
import com.xiaou.xiaoueasyprojectbackend.module.support.monitor.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 部门信息
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/system")
@Validated
@RequiredArgsConstructor
@Tag(name = "部门API", description = "部门相关的增删查改")
public class DeptControllerV1 {

    @Resource
    private DeptApplicationService deptApplicationService;

    /**
     * 获取部门列表
     */
    @Operation(summary = "部门列表")
    @GetMapping("/depts")
    public ResponseDTO<List<DeptDTO>> list(DeptQuery query) {
        List<DeptDTO> deptList = deptApplicationService.getDeptList(query);
        return ResponseDTO.ok(deptList);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @Operation(summary = "部门详情")
    @GetMapping(value = "/dept/{deptId}")
    public ResponseDTO<DeptDTO> getInfo(@PathVariable Long deptId) {
        DeptDTO dept = deptApplicationService.getDeptInfo(deptId);
        return ResponseDTO.ok(dept);
    }

    /**
     * 获取部门下拉树列表
     */
    @Operation(summary = "获取部门树级结构")
    @GetMapping("/depts/dropdown")
    public ResponseDTO<List<Tree<Long>>> dropdownList() {
        List<Tree<Long>> deptTree = deptApplicationService.getDeptTree();
        return ResponseDTO.ok(deptTree);
    }

    /**
     * 新增部门
     */
    @Operation(summary = "新增部门")
    @PostMapping("/dept")
    public ResponseDTO<Void> add(@RequestBody AddDeptCommand addCommand) {
        deptApplicationService.addDept(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改部门
     */
    @Operation(summary = "修改部门")
    @PutMapping("/dept/{deptId}")
    public ResponseDTO<Void> edit(@PathVariable("deptId") Long deptId, @RequestBody UpdateDeptCommand updateCommand) {
        updateCommand.setDeptId(deptId);
        deptApplicationService.updateDept(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除部门
     */
    @Operation(summary = "删除部门")
    @DeleteMapping("/dept/{deptId}")
    public ResponseDTO<Void> remove(@PathVariable @NotNull Long deptId) {
        deptApplicationService.removeDept(deptId);
        return ResponseDTO.ok();
    }
}
