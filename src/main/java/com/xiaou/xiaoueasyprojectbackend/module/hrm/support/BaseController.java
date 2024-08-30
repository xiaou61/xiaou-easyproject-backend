package com.xiaou.xiaoueasyprojectbackend.module.hrm.support;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.xiaou.xiaoueasyprojectbackend.common.constant.HttpStatus;
import com.xiaou.xiaoueasyprojectbackend.common.domain.AjaxResult;

import java.util.List;

public class BaseController {
    /**
     * 获取mp分页
     */
    protected <P> Page<P> getMPPage(P clz) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<P> page = new Page<>();
        // 当前页
        page.setCurrent(pageDomain.getPageNum() == null ? 1 : pageDomain.getPageNum());
        // 当前页显示多少行
        page.setSize(pageDomain.getPageSize() == null ? 10 : pageDomain.getPageSize());
        return page;
    }

    /**
     * 兼容mp
     */
    protected TableDataInfo getDataTableWithPage(Page<?> page) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(page.getRecords());
        rspData.setTotal(page.getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }
    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }
}
