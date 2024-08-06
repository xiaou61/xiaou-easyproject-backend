package com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.search;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单管理查询类
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysMenuSearch对象", description = "菜单管理Search对象")
public class SysMenuSearch {

    private Integer page = 1;
    private Integer size = 10;

    @Schema(description = "父菜单ID")
    private String parentId;

    @Schema(description = "父节点ID路径")
    private String treePath;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单类型(1:菜单；2:目录；3:外链；4:按钮)")
    private Integer menuType;

    @Schema(description = "路由路径(浏览器地址栏路径)")
    private String path;

    @Schema(description = "组件路径(vue页面完整路径，省略.vue后缀)")
    private String component;

    @Schema(description = "权限标识")
    private String perm;

    @Schema(description = "显示状态(1-显示;0-隐藏)")
    private Integer visible;

    @Schema(description = "排序")
    private Integer menuSort;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "跳转路径")
    private String redirect;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "逻辑删除字段")
    private Integer delFlag;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "祖级id路径(parentid/sonid/id)")
    private String ancestors;

    @Schema(description = "路由参数")
    private String queryParams;
}