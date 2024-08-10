package com.xiaou.xiaoueasyprojectbackend.module.support.log.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 分页返回数据公共类
 *
 * @author wangchenghai
 * @date  2023/01/10 14:24:48
 * @param
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ReturnPageData<T> extends PageData{

    @Schema(description = "查询数据列表")
    private List<T> records;

    public static <T> ReturnPageData<T> fillingData(PageData pageData,List<T> records) {
        ReturnPageData<T> returnPageData = new ReturnPageData<>();
        returnPageData.setTotal(pageData.getTotal());
        returnPageData.setSize(pageData.getSize());
        returnPageData.setCurrent(pageData.getCurrent());
        returnPageData.setRecords(records);
        return returnPageData;
    }
}
