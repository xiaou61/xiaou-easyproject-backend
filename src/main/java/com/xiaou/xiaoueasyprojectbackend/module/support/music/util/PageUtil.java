package com.xiaou.xiaoueasyprojectbackend.module.support.music.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.entity.PageQueryInterface;

import java.util.Objects;

public class PageUtil {

    public static <T> IPage<T> buildPage(PageQueryInterface PageQueryInterface) {
        Objects.requireNonNull(PageQueryInterface);
        IPage<T> page = new Page<>(PageQueryInterface.getPage(), PageQueryInterface.getLimit());
        return page;
    }
}
