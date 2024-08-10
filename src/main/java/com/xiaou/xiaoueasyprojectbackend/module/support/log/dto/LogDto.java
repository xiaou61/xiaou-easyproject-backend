package com.xiaou.xiaoueasyprojectbackend.module.support.log.dto;

import java.io.Serializable;
import java.io.Serial;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
* <p>
* 系统日志 DTO 数据传输对象
* </p>
*
* @author xiaohai
* @since 2023-03-30
*/
@Getter
@Setter
@Schema(name = "LogDto", description = "系统日志 DTO 数据传输对象")
public class LogDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "模块名称")
    private String title;

    @Schema(description = "方法名称")
    private String method;

    @Schema(description = "请求方式")
    private String requestMethod;

    @Schema(description = "请求url")
    private String operUrl;

    @Schema(description = "主机地址")
    private String operIp;

    @Schema(description = "请求参数")
    private String operParam;

    @Schema(description = "返回参数")
    private String jsonResult;

    @Schema(description = "错误消息")
    private String errorMsg;

    @Schema(description = "状态（0正常 1异常）")
    private String status;

    @Schema(description = "创建人", example = "创建人")
    private String createdBy;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;
}
