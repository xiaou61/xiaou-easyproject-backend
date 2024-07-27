package com.xiaou.xiaoueasyprojectbackend.module.support.comment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "审核")
public class ReviewVO {

    @NotNull(message = "id不能为空")
    @Schema(description = "id列表", required = true)
    private List<Integer> ids;

    @NotNull(message = "状态值不能为空")
    @Schema(description = "删除状态", required = true)
    private Integer isReview;

}
