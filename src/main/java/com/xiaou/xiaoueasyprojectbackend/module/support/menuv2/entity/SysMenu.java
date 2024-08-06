package com.xiaou.xiaoueasyprojectbackend.module.support.menuv2.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 菜单管理
 *
 * @author CodeGenerator
 * @since 2024-07-23
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
@Schema(name = "SysMenu", description = "菜单管理对象")
public class SysMenu {

    @Schema(description = "PK")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "父菜单ID")
    @TableField("parent_id")
    private String parentId;

    @TableField("tree_path")
    @Schema(description = "父节点ID路径")
    private String treePath;


    @Schema(description = "菜单名称")
    private String name;

    @TableField("menu_type")
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

    @TableField("is_frame")
    @Schema(description = "排序")
    private Integer menuSort;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "跳转路径")
    private String redirect;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
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