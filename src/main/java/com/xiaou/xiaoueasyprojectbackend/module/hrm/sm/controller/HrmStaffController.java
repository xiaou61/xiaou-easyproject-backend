package com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;
import com.xiaou.xiaoueasyprojectbackend.common.utils.StringUtils;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.md.domain.HrmRank;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.domain.HrmStaff;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.service.IHrmStaffService;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.sm.vo.HrmStaffVo;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.BaseController;
import com.xiaou.xiaoueasyprojectbackend.module.hrm.support.TableDataInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiaou61
 * @Date 2024/8/30 18:26
 * @Description 员工花名册接口
 */
@RestController
@RequestMapping("/hrm/sm/hrm-staff")
public class HrmStaffController extends BaseController {



    @Resource
    private IHrmStaffService hrmStaffService;


    @GetMapping("/listTalents")
    public TableDataInfo listTalents(HrmStaff hrmStaff) {
        List<HrmStaffVo> list =  hrmStaffService.listTalents(hrmStaff);
        return getDataTable(list);
    }

    /**
     * 查询员工花名册列表
     */

    @GetMapping("/list")
    public TableDataInfo list(HrmStaff hrmStaff) {
        // 获取查询条件
        LambdaQueryWrapper<HrmStaff> queryWrapper = getQueryWrapper(hrmStaff);
        // 组装分页
        Page<HrmStaff> page = getMPPage(hrmStaff);
        // 查询
        this.hrmStaffService.page(page, queryWrapper);
        return getDataTableWithPage(page);
    }

    /**
     * 导出员工花名册列表
     */
    @SneakyThrows
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrmStaff hrmStaff) {
        // 获取查询条件
        LambdaQueryWrapper<HrmStaff> queryWrapper = getQueryWrapper(hrmStaff);
        List<HrmStaff> list = this.hrmStaffService.list(queryWrapper);


        //这里是用easyexcel导出 下载路径可以自己修改
        FileOutputStream outputStream = new FileOutputStream(new File("D:/员工花名册数据.xlsx"));
        EasyExcel.write(outputStream, HrmRank.class).sheet("员工花名册数据").doWrite(list);
    }


    /**
     * 获取员工花名册详细信息
     */

    @GetMapping(value = "/{staffId}")
    public AjaxResult getInfo(@PathVariable("staffId") Long staffId) {
        return AjaxResult.success(this.hrmStaffService.getById(staffId));
    }

    /**
     * 获取人才详情信息
     */

    @GetMapping(value = "/getTalent/{staffId}")
    public AjaxResult getTalents(@PathVariable("staffId") Long staffId) {
        return AjaxResult.success(this.hrmStaffService.getTalents(staffId));
    }

    /**
     * 新增员工花名册
     */

    @PostMapping
    public AjaxResult add(@RequestBody HrmStaff hrmStaff) {
        return toAjax(this.hrmStaffService.save(hrmStaff));
    }

    /**
     * 修改员工花名册
     */

    @PutMapping
    public AjaxResult edit(@RequestBody HrmStaff hrmStaff) {
        return toAjax(this.hrmStaffService.updateById(hrmStaff));
    }


    /**
     * 员工流程审批
     *
     */

    @PostMapping("/process")
    public AjaxResult process(@RequestBody HrmStaff hrmStaff) {
        this.hrmStaffService.process(hrmStaff);
        return toAjax(this.hrmStaffService.updateById(hrmStaff));
    }

    /**
     * 删除员工花名册
     */

    @DeleteMapping("/{staffIds}")
    public AjaxResult remove(@PathVariable List<Long> staffIds) {
        return toAjax(this.hrmStaffService.lambdaUpdate().in(HrmStaff::getStaffId,staffIds).set(HrmStaff::getDeleted,Boolean.TRUE).set(HrmStaff::getDeleteAt,new Date()).update());
    }

    /**
    * 获取查询条件
    */
    public LambdaQueryWrapper<HrmStaff> getQueryWrapper(HrmStaff hrmStaff) {
        LambdaQueryWrapper<HrmStaff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(hrmStaff.getStaffCode()), HrmStaff::getStaffCode, hrmStaff.getStaffCode());
        queryWrapper.like(StringUtils.isNotEmpty(hrmStaff.getStaffName()), HrmStaff::getStaffName, hrmStaff.getStaffName());
/*        queryWrapper.eq(hrmStaff.getContactPhone() != null, HrmStaff::getContactPhone, hrmStaff.getContactPhone());
        queryWrapper.eq(hrmStaff.getSex() != null, HrmStaff::getSex, hrmStaff.getSex());
        queryWrapper.eq(hrmStaff.getEthnicity() != null, HrmStaff::getEthnicity, hrmStaff.getEthnicity());
        queryWrapper.eq(hrmStaff.getBirthDate() != null, HrmStaff::getBirthDate, hrmStaff.getBirthDate());
        queryWrapper.eq(hrmStaff.getEmail() != null, HrmStaff::getEmail, hrmStaff.getEmail());
        queryWrapper.eq(hrmStaff.getMaritalStatus() != null, HrmStaff::getMaritalStatus, hrmStaff.getMaritalStatus());
        queryWrapper.eq(hrmStaff.getPoliticalOutlook() != null, HrmStaff::getPoliticalOutlook, hrmStaff.getPoliticalOutlook());
        queryWrapper.eq(hrmStaff.getIdCard() != null, HrmStaff::getIdCard, hrmStaff.getIdCard());
        queryWrapper.eq(hrmStaff.getOrigin() != null, HrmStaff::getOrigin, hrmStaff.getOrigin());
        queryWrapper.eq(hrmStaff.getHouseholdAddress() != null, HrmStaff::getHouseholdAddress, hrmStaff.getHouseholdAddress());
        queryWrapper.eq(hrmStaff.getCurrentAddress() != null, HrmStaff::getCurrentAddress, hrmStaff.getCurrentAddress());
        queryWrapper.eq(hrmStaff.getEmergencyContact() != null, HrmStaff::getEmergencyContact, hrmStaff.getEmergencyContact());
        queryWrapper.eq(hrmStaff.getRelationship() != null, HrmStaff::getRelationship, hrmStaff.getRelationship());
        queryWrapper.eq(hrmStaff.getEmergencyContactPhone() != null, HrmStaff::getEmergencyContactPhone, hrmStaff.getEmergencyContactPhone());*/
        queryWrapper.eq(hrmStaff.getEducation() != null, HrmStaff::getEducation, hrmStaff.getEducation());
/*        queryWrapper.eq(hrmStaff.getJoinedTime() != null, HrmStaff::getJoinedTime, hrmStaff.getJoinedTime());
        queryWrapper.eq(hrmStaff.getLeaveTime() != null, HrmStaff::getLeaveTime, hrmStaff.getLeaveTime());*/
        queryWrapper.eq(hrmStaff.getStatus() != null, HrmStaff::getStatus, hrmStaff.getStatus());
        // 默认创建时间倒序
        queryWrapper.orderByDesc(HrmStaff::getCreateTime);
        Map<String, Object> params = hrmStaff.getParams();
        queryWrapper.between(params.get("beginTime") != null && params.get("endTime") != null,HrmStaff::getCreateTime, params.get("beginTime"), params.get("endTime"));
        return queryWrapper;
    }
}
