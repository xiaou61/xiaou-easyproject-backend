package com.xiaou.xiaoueasyprojectbackend.module.support.music.param;

import com.xiaou.xiaoueasyprojectbackend.module.support.music.entity.PageQueryInterface;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageQueryParam implements PageQueryInterface {

    /**
     * 当前页码
     */
    @Min(value = 1, message = "当前页码不允许小于第一页")
    @NotNull(message = "page不允许为空")
    private Integer page;

    /**
     * 每页显示总记录数
     */
    @Min(value = 0, message = "当前页总记录数不允许为负数")
    @NotNull(message = "limit不允许为空")
    private Integer limit;
}