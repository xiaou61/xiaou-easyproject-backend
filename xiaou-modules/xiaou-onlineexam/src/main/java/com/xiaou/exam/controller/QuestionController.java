package com.xiaou.exam.controller;

import com.xiaou.exam.model.entity.Question;
import com.xiaou.exam.model.req.BatchDeleteRequest;
import com.xiaou.exam.model.req.QuestionFrom;
import com.xiaou.exam.model.vo.QuestionVO;
import com.xiaou.exam.service.IQuestionService;
import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
import com.xiaou.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xnio.Result;

@Tag(name = "试题管理相关接口")
@RestController
@RequestMapping("/exam/questions")
public class QuestionController {
    @Resource
    private IQuestionService iQuestionService;


    /**
     * 单题添加
     * @return 响应
     */
    @Operation(summary = "单题添加")
    @PostMapping("/single")
    public R<String> addSingleQuestion(@RequestBody QuestionFrom questionFrom) {
        return iQuestionService.addSingleQuestion(questionFrom);
    }


    @Operation(summary = "批量删除试题")
    @DeleteMapping("/batch")
    public R<String> deleteBatchQuestion(@RequestBody BatchDeleteRequest request) {
        return iQuestionService.deleteBatchByIds(request);
    }


    /**
     * 分页查询
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public R<PageRespDto<Question>> pageQuestion(@ParameterObject PageReqDto req) {
        return iQuestionService.pageQuestion(req);
    }

    /**
     * 根据试题id获取单题详情
     *
     * @param id 试题id
     * @return 响应结果
     */
    @Operation(summary = "根据试题id获取单题详情")
    @GetMapping("/single/{id}")
    public R<QuestionVO> querySingle(@PathVariable("id") Integer id) {
        return iQuestionService.querySingle(id);
    }

    /**
     * 修改试题
     *
     * @param id           试题Id
     * @param questionFrom 入参
     * @return 响应结果
     */
    @Operation(summary = "修改试题")
    @PutMapping("/{id}")
    public R<String> updateQuestion(@PathVariable("id") Integer id, @RequestBody QuestionFrom questionFrom) {
        questionFrom.setId(id);
        return iQuestionService.updateQuestion(questionFrom);
    }

    /**
     * 批量导入试题
     *
     * @param id   题库Id
     * @param file Excel文件
     * @return 响应结果
     */
    @Operation(summary = "批量导入试题")
    @PostMapping("/import/{id}")
    public R<String> importQuestion(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) {
        return iQuestionService.importQuestion(id, file);
    }


}
