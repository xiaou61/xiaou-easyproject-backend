package com.xiaou.xiaoueasyprojectbackend.module.support.Ai.controller;

import com.xiaou.xiaoueasyprojectbackend.module.support.AIGCTongyi.utils.AiUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xiaou61
 * @Date 2024/7/31 11:47
 * @Description 一个Ai接口合集 目前包含了文本纠错 文本识别
 */
@RestController
@RequestMapping("/v1/ai")
@Tag(name = "Ai接口")
public class AiController {
    @GetMapping(value = "/nlp",produces = "application/json;charset=utf-8")
    public Result nlp(@RequestParam("text") String text){
        String res = AiUtils.nlp(text);
        return Result.success(res);
    }

    @PostMapping(value = "/word", produces = "application/json;charset=utf-8")
    public Result nlp(@RequestParam("file") MultipartFile file) throws IOException {
        String res = AiUtils.word(file);
        return Result.success(res);
    }
}
