package com.xiaou.novel.manager;

import com.xiaou.novel.entity.po.BookChapter;
import com.xiaou.novel.entity.resp.BookChapterRespDto;
import com.xiaou.novel.mapper.BookChapterMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookChapterManager {
    @Resource
    private BookChapterMapper bookChapterMapper;

    public BookChapterRespDto getChapter(Long chapterId) {
        BookChapter bookChapter = bookChapterMapper.selectById(chapterId);
        if (Objects.isNull(bookChapter)) {
            return null;
        }
        return BookChapterRespDto.builder()
                .id(chapterId)
                .bookId(bookChapter.getBookId())
                .chapterNum(bookChapter.getChapterNum())
                .chapterName(bookChapter.getChapterName())
                .chapterWordCount(bookChapter.getWordCount())
                .chapterUpdateTime(bookChapter.getUpdateTime())
                .isVip(bookChapter.getIsVip())
                .build();
    }
}
