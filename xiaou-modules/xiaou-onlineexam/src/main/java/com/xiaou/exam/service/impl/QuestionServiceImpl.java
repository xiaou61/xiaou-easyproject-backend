package com.xiaou.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.exam.mapper.OptionMapper;
import com.xiaou.exam.mapper.QuestionMapper;
import com.xiaou.exam.model.entity.Option;
import com.xiaou.exam.model.entity.Question;
import com.xiaou.exam.model.req.QuestionFrom;
import com.xiaou.exam.service.IQuestionService;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {


    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private OptionMapper optionMapper;


    @Override
    public R<String> addSingleQuestion(QuestionFrom questionFrom) {
        return null;
    }
}
