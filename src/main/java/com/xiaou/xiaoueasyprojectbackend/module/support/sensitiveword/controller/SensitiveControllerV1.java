package com.xiaou.xiaoueasyprojectbackend.module.support.sensitiveword.controller;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import com.github.houbb.sensitive.word.support.resultcondition.WordResultConditions;
import com.github.houbb.sensitive.word.support.tag.WordTags;
import com.xiaou.xiaoueasyprojectbackend.common.utils.ResultUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaou61
 * @Date 2024/7/27 16:02
 * @Description 敏感词检测功能 基于开源项目 https://github.com/houbb/sensitive-word
 */
@RestController
@RequestMapping("/v1/sensitive")
public class SensitiveControllerV1 {
    @GetMapping
    public BaseResponse test(@RequestParam String content) {

        //默认敏感词替换为***
        String result = SensitiveWordHelper.replace(content);
        return ResultUtils.success(result);
    }
}
