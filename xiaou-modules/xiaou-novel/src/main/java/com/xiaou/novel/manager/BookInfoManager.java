package com.xiaou.novel.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.novel.constants.DatabaseConsts;
import com.xiaou.novel.entity.po.BookChapter;
import com.xiaou.novel.entity.po.BookInfo;
import com.xiaou.novel.entity.resp.BookInfoRespDto;
import com.xiaou.novel.mapper.BookChapterMapper;
import com.xiaou.novel.mapper.BookInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class BookInfoManager {

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Resource
    private BookChapterMapper bookChapterMapper;

    public BookInfoRespDto getBookInfo(Long id) {
        return PutBookInfo(id);
    }

    public BookInfoRespDto PutBookInfo(Long id) {


        // 查询基础信息
        BookInfo bookInfo = bookInfoMapper.selectById(id);
        if (Objects.isNull(bookInfo)) {
            return null;
        }

        // 查询首章ID
        QueryWrapper<BookChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(DatabaseConsts.BookChapterTable.COLUMN_BOOK_ID, id)
                .orderByAsc(DatabaseConsts.BookChapterTable.COLUMN_CHAPTER_NUM)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookChapter firstBookChapter = bookChapterMapper.selectOne(queryWrapper);
        // 组装响应对象
        return BookInfoRespDto.builder()
                .id(bookInfo.getId())
                .bookName(bookInfo.getBookName())
                .bookDesc(bookInfo.getBookDesc())
                .bookStatus(bookInfo.getBookStatus())
                .authorId(bookInfo.getAuthorId())
                .authorName(bookInfo.getAuthorName())
                .categoryId(bookInfo.getCategoryId())
                .categoryName(bookInfo.getCategoryName())
                .commentCount(bookInfo.getCommentCount())
                .firstChapterId(firstBookChapter.getId())
                .lastChapterId(bookInfo.getLastChapterId())
                .picUrl(bookInfo.getPicUrl())
                .visitCount(bookInfo.getVisitCount())
                .wordCount(bookInfo.getWordCount())
                .build();
    }


    public List<Long> getLastUpdateIdList(Long categoryId) {
        QueryWrapper<BookInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookTable.COLUMN_CATEGORY_ID, categoryId)
                .gt(DatabaseConsts.BookTable.COLUMN_WORD_COUNT, 0)
                .orderByDesc(DatabaseConsts.BookTable.COLUMN_LAST_CHAPTER_UPDATE_TIME)
                .last(DatabaseConsts.SqlEnum.LIMIT_500.getSql());
        return bookInfoMapper.selectList(queryWrapper).stream().map(BookInfo::getId).toList();
    }
}
