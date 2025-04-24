package com.xiaou.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.exam.model.entity.Question;
import com.xiaou.exam.model.req.QuestionFrom;
import com.xiaou.utils.R;
import org.xnio.Result;

public interface IQuestionService extends IService<Question> {
    /**
     * 单题添加
     *
     * @param questionFrom 传参
     * @return 响应
     */
    R<String> addSingleQuestion(QuestionFrom questionFrom);
}
