package com.xiaou.exam.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import com.xiaou.exam.model.req.QuestionExcelFrom;
import com.xiaou.exam.model.req.QuestionFrom;
import com.xiaou.exam.model.vo.QuestionVO;
import com.xiaou.exam.service.IQuestionService;
import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

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

    @Override
    public R<QuestionVO> querySingle(Integer id) {
        QuestionVO result = questionMapper.selectSingle(id);
        return R.ok("根据试题id获取单题详情成功", result);
    }

    @Override
    public R<String> updateQuestion(QuestionFrom questionFrom) {
        // 修改试题
        Question question = questionConverter.fromToEntity(questionFrom);
        questionMapper.updateById(question);
        // 修改选项
        List<Option> options = questionFrom.getOptions();
        // 流式 API 批量更新选项，简洁清晰
        Optional.ofNullable(questionFrom.getOptions())
                .orElse(Collections.emptyList())
                .forEach(optionMapper::updateById);
        return R.ok("修改试题成功");
    }

    @Override
    public R<String> importQuestion(Integer id, MultipartFile file) {
        // 判断是否为 Excel 文件
        if (!file.getOriginalFilename().endsWith(".xls") && !file.getOriginalFilename().endsWith(".xlsx")) {
            throw new RuntimeException("该文件不是一个合法的Excel文件");
        }

        List<QuestionExcelFrom> questionExcelFroms;
        try {
            // 使用 EasyExcel 读取数据
            questionExcelFroms = EasyExcel.read(file.getInputStream(), QuestionExcelFrom.class, null).sheet().doReadSync();
        } catch (IOException e) {
            throw new RuntimeException("读取 Excel 文件失败", e);
        }

        // 类型转换
        List<QuestionFrom> list = QuestionExcelFrom.converterQuestionFrom(questionExcelFroms);

        for (QuestionFrom questionFrom : list) {
            Question question = questionConverter.fromToEntity(questionFrom);
            question.setRepoId(id);
            question.setCreateTime(LocalDateTime.now());

            // 插入题目
            questionMapper.insert(question);

            // 批量添加选项
            List<Option> options = questionFrom.getOptions();
            final int[] count = {0};
            options.forEach(option -> {
                if (question.getQuType() == 4) { // 简答题默认正确
                    option.setIsRight(1);
                }
                option.setSort(++count[0]);
                option.setQuId(question.getId());
            });

            if (!options.isEmpty()) {
                optionMapper.insertBatch(options);
            }
        }

        return R.ok("导入试题成功");
    }





}
