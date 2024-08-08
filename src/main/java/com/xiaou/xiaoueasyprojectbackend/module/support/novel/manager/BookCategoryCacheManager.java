package com.xiaou.xiaoueasyprojectbackend.module.support.novel.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xiaou.xiaoueasyprojectbackend.module.support.novel.constants.CacheConsts;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.constants.DatabaseConsts;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.dto.BookCategoryRespDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.entity.BookCategory;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.mapper.BookCategoryMapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 小说分类 缓存管理类
 *
 * @author xiongxiaoyang
 * @date 2022/5/12
 */
@Component
public class BookCategoryCacheManager {

    @Resource
    private BookCategoryMapper bookCategoryMapper;

    /**
     * 根据作品方向查询小说分类列表，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
        value = CacheConsts.BOOK_CATEGORY_LIST_CACHE_NAME)
    public List<BookCategoryRespDto> listCategory(Integer workDirection) {
        QueryWrapper<BookCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookCategoryTable.COLUMN_WORK_DIRECTION, workDirection);
        return bookCategoryMapper.selectList(queryWrapper).stream().map(v ->
            BookCategoryRespDto.builder()
                .id(v.getId())
                .name(v.getName())
                .build()).toList();
    }

}
