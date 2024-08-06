package com.xiaou.xiaoueasyprojectbackend.module.support.music.param;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * @projectName: woodwhales-music
 * @author: woodwhales
 * @date: 20.8.9 18:06
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageMusicQueryRequestParam extends PageQueryParam {

    private String searchInfo;

    private OrderBy orderBy;

    @Data
    public static class OrderBy {
        private String order;
        private String prop;
    }

    public boolean emptyOrderBy() {
        return Objects.isNull(this.orderBy) || (Objects.isNull(this.orderBy.order) &&
                Objects.isNull(this.orderBy.prop));
    }

}
