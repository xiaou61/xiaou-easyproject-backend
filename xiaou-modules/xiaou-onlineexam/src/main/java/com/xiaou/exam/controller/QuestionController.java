package com.xiaou.exam.controller;

import com.xiaou.exam.model.req.QuestionFrom;
import com.xiaou.exam.service.IQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Result<String> addSingleQuestion(@RequestBody QuestionFrom questionFrom) {
        return iQuestionService.addSingleQuestion(questionFrom);
    }


}
