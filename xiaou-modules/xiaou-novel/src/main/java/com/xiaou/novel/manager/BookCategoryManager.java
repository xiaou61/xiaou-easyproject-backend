package com.xiaou.novel.manager;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.novel.constants.DatabaseConsts;
import com.xiaou.novel.entity.po.BookCategory;
import com.xiaou.novel.entity.resp.BookCategoryRespDto;
import com.xiaou.novel.mapper.BookCategoryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookCategoryManager {

    @Resource
    private BookCategoryMapper bookCategoryMapper;

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
