package com.xiaou.novel.controller.anthor;

import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
import com.xiaou.novel.entity.dto.ChapterUpdateReqDto;
import com.xiaou.novel.entity.req.AuthorRegisterReqDto;
import com.xiaou.novel.entity.req.BookAddReqDto;
import com.xiaou.novel.entity.req.ChapterAddReqDto;
import com.xiaou.novel.entity.resp.BookChapterRespDto;
import com.xiaou.novel.entity.resp.BookInfoRespDto;
import com.xiaou.novel.entity.resp.ChapterContentRespDto;
import com.xiaou.novel.service.AuthorService;
import com.xiaou.novel.service.BookService;
import com.xiaou.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/novel/author")
public class AuthorController {


    @Resource
    private AuthorService authorService;

    @Resource
    private BookService bookService;

    /**
     * 作家注册接口
     */
    @PostMapping("register")
    @Operation(summary = "作家注册接口")
    public R<Void> register(@Valid @RequestBody AuthorRegisterReqDto dto) {
        //这里根据实际开发去获取当前用户
        dto.setUserId(1L);
        return authorService.register(dto);
    }

    @Operation(summary = "作家状态查询接口")
    @GetMapping("status")
    public R<Integer> getStatus() {
        return authorService.getStatus(1L);
    }

    /**
     * 小说发布接口
     */
    @Operation(summary = "小说发布接口")
    @PostMapping("book")
    public R<Void> publishBook(@Valid @RequestBody BookAddReqDto dto) {
        return bookService.saveBook(dto);
    }


    @Operation(summary = "小说发布列表查询接口")
    @GetMapping("books")
    public R<PageRespDto<BookInfoRespDto>> listBooks(@ParameterObject PageReqDto dto) {
        return bookService.listAuthorBooks(dto);
    }


    /**
     * 小说章节发布接口
     */
    @Operation(summary = "小说章节发布接口")
    @PostMapping("book/chapter/{bookId}")
    public R<Void> publishBookChapter(
            @Parameter(description = "小说ID") @PathVariable("bookId") Long bookId,
            @Valid @RequestBody ChapterAddReqDto dto) {
        dto.setBookId(bookId);
        return bookService.saveBookChapter(dto);
    }


    /**
     * 小说章节删除接口
     */
    @Operation(summary = "小说章节删除接口")
    @DeleteMapping("book/chapter/{chapterId}")
    public R<Void> deleteBookChapter(
            @Parameter(description = "章节ID") @PathVariable("chapterId") Long chapterId) {
        return bookService.deleteBookChapter(chapterId);
    }

    /**
     * 小说章节查询接口
     */
    @Operation(summary = "小说章节查询接口")
    @GetMapping("book/chapter/{chapterId}")
    public R<ChapterContentRespDto> getBookChapter(
            @Parameter(description = "章节ID") @PathVariable("chapterId") Long chapterId) {
        return bookService.getBookChapter(chapterId);
    }


    @Operation(summary = "小说章节更新接口")
    @PutMapping("book/chapter/{chapterId}")
    public R<Void> updateBookChapter(
            @Parameter(description = "章节ID") @PathVariable("chapterId") Long chapterId,
            @Valid @RequestBody ChapterUpdateReqDto dto) {
        return bookService.updateBookChapter(chapterId, dto);
    }


    /**
     * 小说章节发布列表查询接口
     */
    @Operation(summary = "小说章节发布列表查询接口")
    @GetMapping("book/chapters/{bookId}")
    public R<PageRespDto<BookChapterRespDto>> listBookChapters(
            @Parameter(description = "小说ID") @PathVariable("bookId") Long bookId,
            @ParameterObject PageReqDto dto) {
        return bookService.listBookChapters(bookId, dto);
    }
}
