package com.xiaou.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
import org.xnio.Result;

import java.util.List;

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


}
