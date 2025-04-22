package com.xiaou.novel.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 章节发布 请求DTO
 */
@Data
public class ChapterUpdateReqDto {

    /**
     * 章节名
     */
    @NotBlank
    @Schema(description = "章节名", required = true)
    private String chapterName;

    /**
     * 章节内容
     */
    @Schema(description = "章节内容", required = true)
    @NotBlank
    private String chapterContent;

    /**
     * 是否收费;1-收费 0-免费
     */
    @Schema(description = "是否收费;1-收费 0-免费", required = true)
    @NotNull
    private Integer isVip;

}
