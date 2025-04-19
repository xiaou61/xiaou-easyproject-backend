package com.xiaou.novel.service;

import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
import com.xiaou.novel.entity.dto.ChapterUpdateReqDto;
import com.xiaou.novel.entity.req.BookAddReqDto;
import com.xiaou.novel.entity.req.ChapterAddReqDto;
import com.xiaou.novel.entity.resp.BookChapterRespDto;
import com.xiaou.novel.entity.resp.BookInfoRespDto;
import com.xiaou.novel.entity.resp.ChapterContentRespDto;
import com.xiaou.utils.R;

public interface BookService {

    /**
     * 小说信息保存
     *
     * @param dto 小说信息
     * @return void
     */
    R<Void> saveBook(BookAddReqDto dto);

    /**
     * 查询作家发布小说列表
     *
     * @param dto 分页请求参数
     * @return 小说分页列表数据
     */
    R<PageRespDto<BookInfoRespDto>> listAuthorBooks(PageReqDto dto);

    /**
     * 小说章节信息保存
     *
     * @param dto 章节信息
     * @return void
     */
    R<Void> saveBookChapter(ChapterAddReqDto dto);

    /**
     * 小说章节删除
     *
     * @param chapterId 章节ID
     * @return void
     */
    R<Void> deleteBookChapter(Long chapterId);


    /**
     * 小说章节查询
     *
     * @param chapterId 章节ID
     * @return 章节内容
     */
    R<ChapterContentRespDto> getBookChapter(Long chapterId);

    /**
     * 小说章节更新
     *
     * @param chapterId 章节ID
     * @param dto       更新内容
     * @return void
     */
    R<Void> updateBookChapter(Long chapterId, ChapterUpdateReqDto dto);


    /**
     * 查询小说发布章节列表
     *
     * @param bookId 小说ID
     * @param dto    分页请求参数
     * @return 章节分页列表数据
     */
    R<PageRespDto<BookChapterRespDto>> listBookChapters(Long bookId, PageReqDto dto);




}
