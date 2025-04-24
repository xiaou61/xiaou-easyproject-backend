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


//    @Override
//    @Transactional
//    public R<String> addSingleQuestion(QuestionFrom questionFrom) {
//        // 入参校验
//        List<Option> options = questionFrom.getOptions();
//        if (questionFrom.getQuType() != 4 && (Objects.isNull(options) || options.size() < 2)) {
//            return R.fail("非简答题的试题选项不能少于两个");
//        }
//        Question question = questionConverter.fromToEntity(questionFrom);
//        // 开始添加题干
//        questionMapper.insert(question);
//        // 根据试题类型添加选项
//        if (question.getQuType() == 4) {
//            // 简答题添加选项
//            Option option = questionFrom.getOptions().get(0);
//            option.setQuId(question.getId());
//            optionMapper.insert(option);
//        } else {
//            // 非简答题添加选项
//            // 把新建试题获取的id，填入选项中
//            options.forEach(option -> {
//                option.setQuId(question.getId());
//            });
//            optionMapper.insertBatch(options);
//        }
//        return R.ok("添加成功");
//    }
}
