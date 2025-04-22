package com.xiaou.novel.controller.front;

import com.xiaou.novel.entity.resp.*;
import com.xiaou.novel.service.BookService;
import com.xiaou.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/novel/book")
/**
 * 前台门户
 */
public class BookController {

    @Resource
    private BookService bookService;


    /**
     * 小说分类列表查询接口
     */
    @Operation(summary = "小说分类列表查询接口")
    @GetMapping("category/list")
    public R<List<BookCategoryRespDto>> listCategory(
            @Parameter(description = "作品方向", required = true) Integer workDirection) {
        return bookService.listCategory(workDirection);
    }

    /**
     * 小说信息查询接口
     */
    @Operation(summary = "小说信息查询接口")
    @GetMapping("{id}")
    public R<BookInfoRespDto> getBookById(
            @Parameter(description = "小说 ID") @PathVariable("id") Long bookId) {
        return bookService.getBookById(bookId);
    }


    /**
     * 增加小说点击量接口
     */
    @Operation(summary = "增加小说点击量接口")
    @PostMapping("visit")
    public R<Void> addVisitCount(@Parameter(description = "小说ID") Long bookId) {
        return bookService.addVisitCount(bookId);
    }


    /**
     * 小说最新章节相关信息查询接口
     */
    @Operation(summary = "小说最新章节相关信息查询接口")
    @GetMapping("last_chapter/about")
    public R<BookChapterAboutRespDto> getLastChapterAbout(
            @Parameter(description = "小说ID") Long bookId) {
        return bookService.getLastChapterAbout(bookId);
    }

    /**
     * 小说推荐列表查询接口
     */
    @Operation(summary = "小说推荐列表查询接口")
    @GetMapping("rec_list")
    public R<List<BookInfoRespDto>> listRecBooks(
            @Parameter(description = "小说ID") Long bookId) throws NoSuchAlgorithmException {
        return bookService.listRecBooks(bookId);
    }

    /**
     * 小说章节列表查询接口
     */
    @Operation(summary = "小说章节列表查询接口")
    @GetMapping("chapter/list")
    public R<List<BookChapterRespDto>> listChapters(
            @Parameter(description = "小说ID") Long bookId) {
        return bookService.listChapters(bookId);
    }

    /**
     * 小说内容相关信息查询接口
     */
    @Operation(summary = "小说内容相关信息查询接口")
    @GetMapping("content/{chapterId}")
    public R<BookContentAboutRespDto> getBookContentAbout(
            @Parameter(description = "章节ID") @PathVariable("chapterId") Long chapterId) {
        return bookService.getBookContentAbout(chapterId);
    }



    /**
     * 获取上一章节ID接口
     */
    @Operation(summary = "获取上一章节ID接口")
    @GetMapping("pre_chapter_id/{chapterId}")
    public R<Long> getPreChapterId(
            @Parameter(description = "章节ID") @PathVariable("chapterId") Long chapterId) {
        return bookService.getPreChapterId(chapterId);
    }

    /**
     * 获取下一章节ID接口
     */
    @Operation(summary = "获取下一章节ID接口")
    @GetMapping("next_chapter_id/{chapterId}")
    public R<Long> getNextChapterId(
            @Parameter(description = "章节ID") @PathVariable("chapterId") Long chapterId) {
        return bookService.getNextChapterId(chapterId);
    }


    /**
     * 小说点击榜查询接口
     */
    @Operation(summary = "小说点击榜查询接口")
    @GetMapping("visit_rank")
    public R<List<BookRankRespDto>> listVisitRankBooks() {
        return bookService.listVisitRankBooks();
    }



}
