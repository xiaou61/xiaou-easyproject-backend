package com.xiaou.novel.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.novel.constants.DatabaseConsts;
import com.xiaou.novel.entity.po.BookContent;
import com.xiaou.novel.mapper.BookContentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookContentManager {

    @Resource
    private BookContentMapper bookContentMapper;

    public String getBookContent(Long chapterId) {
        QueryWrapper<BookContent> contentQueryWrapper = new QueryWrapper<>();
        contentQueryWrapper.eq(DatabaseConsts.BookContentTable.COLUMN_CHAPTER_ID, chapterId)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookContent bookContent = bookContentMapper.selectOne(contentQueryWrapper);
        if (Objects.isNull(bookContent)) {
            return null;
        }
        return bookContent.getContent();
    }

}
