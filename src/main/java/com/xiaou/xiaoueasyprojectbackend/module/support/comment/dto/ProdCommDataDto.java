package com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "商品评论数据")
@Data
public class ProdCommDataDto {

    @Schema(description = "好评率" )
    private Double positiveRating;

    @Schema(description = "评论数量" )
    private Integer number;

    @Schema(description = "好评数" )
    private Integer praiseNumber;

    @Schema(description = "中评数" )
    private Integer secondaryNumber;

    @Schema(description = "差评数" )
    private Integer negativeNumber;

    @Schema(description = "有图数" )
    private Integer picNumber;

}