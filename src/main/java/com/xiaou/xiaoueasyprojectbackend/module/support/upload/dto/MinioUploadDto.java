package com.xiaou.xiaoueasyprojectbackend.module.support.upload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class MinioUploadDto {
    @Schema(description = "文件访问URL")
    private String url;
    @Schema(description = "文件名称")
    private String name;
}
