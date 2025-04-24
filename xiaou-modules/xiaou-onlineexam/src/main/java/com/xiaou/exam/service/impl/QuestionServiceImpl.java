package com.xiaou.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.exam.converter.QuestionConverter;
import com.xiaou.exam.mapper.OptionMapper;
import com.xiaou.exam.mapper.QuestionMapper;
import com.xiaou.exam.model.entity.Option;
import com.xiaou.exam.model.entity.Question;
import com.xiaou.exam.model.req.BatchDeleteRequest;
import com.xiaou.exam.model.req.QuestionFrom;
import com.xiaou.exam.model.vo.QuestionVO;
import com.xiaou.exam.service.IQuestionService;
import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
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

    @Resource
    private QuestionConverter questionConverter;


    @Override
    @Transactional
    public R<String> addSingleQuestion(QuestionFrom questionFrom) {
        // 入参校验
        List<Option> options = questionFrom.getOptions();
        if (questionFrom.getQuType() != 4 && (Objects.isNull(options) || options.size() < 2)) {
            return R.fail("非简答题的试题选项不能少于两个");
        }

        // 转换 QuestionFrom 为实体对象
        Question question = questionConverter.fromToEntity(questionFrom);

        // 插入题干
        questionMapper.insert(question);

        // 根据题型处理选项
        if (question.getQuType() == 4) {
            // 简答题只有一个选项，直接添加
            Option option = questionFrom.getOptions().get(0);
            option.setQuId(question.getId());
            optionMapper.insert(option);
            return R.ok("简答题添加完成");
        } else {
            // 非简答题添加选项
            // 把新建试题获取的id，填入选项中
            options.forEach(option -> {
                option.setQuId(question.getId());
            });
            optionMapper.insertBatch(options);
        }

        // 返回成功信息
        return R.ok("单题添加成功");
    }

    @Override
    @Transactional
    public R<String> deleteBatchByIds(BatchDeleteRequest req) {
        List<Integer> ids = req.getIds();

        // 批量删除选项
        if (!ids.isEmpty()) {
            QueryWrapper<Option> optionQueryWrapper = new QueryWrapper<>();
            optionQueryWrapper.in("qu_id", ids);  // 一次性删除所有相关选项
            optionMapper.delete(optionQueryWrapper);
        }

        // 批量删除问题
        questionMapper.deleteByIds(ids);

        return R.ok("批量删除成功");
    }

    @Override
    public R<PageRespDto<Question>> pageQuestion(PageReqDto dto) {
        IPage<Question> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());

        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);

        IPage<Question> questionIPage = questionMapper.selectPage(page, queryWrapper);
        return R.ok(PageRespDto.of(dto.getPageNum(), dto.getPageSize(), page.getTotal(),
                questionIPage.getRecords()));
    }


}
