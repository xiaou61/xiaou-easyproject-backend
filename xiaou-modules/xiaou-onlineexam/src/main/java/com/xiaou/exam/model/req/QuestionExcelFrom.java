package com.xiaou.exam.model.req;

import com.alibaba.excel.annotation.ExcelProperty;
import com.xiaou.exam.model.entity.Option;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionExcelFrom {

    @ExcelProperty("试题类型")
    private Integer quType;

    @ExcelProperty("题干")
    private String content;

    @ExcelProperty("解析")
    private String analysis;

    @ExcelProperty("选项一内容")
    private String option1;

    @ExcelProperty("选项一是否正确")
    private Integer righted1;

    @ExcelProperty("选项二内容")
    private String option2;

    @ExcelProperty("选项二是否正确")
    private Integer righted2;

    @ExcelProperty("选项三内容")
    private String option3;

    @ExcelProperty("选项三是否正确")
    private Integer righted3;

    @ExcelProperty("选项四内容")
    private String option4;

    @ExcelProperty("选项四是否正确")
    private Integer righted4;

    @ExcelProperty("选项五内容")
    private String option5;

    @ExcelProperty("选项五是否正确")
    private Integer righted5;

    @ExcelProperty("选项六内容")
    private String option6;

    @ExcelProperty("选项六是否正确")
    private Integer righted6;


    public static List<QuestionFrom> converterQuestionFrom(List<QuestionExcelFrom> questionExcelFroms) {
        List<QuestionFrom> list = new ArrayList<>(questionExcelFroms.size());

        for (QuestionExcelFrom q : questionExcelFroms) {
            QuestionFrom questionFrom = new QuestionFrom();
            questionFrom.setContent(q.getContent());
            questionFrom.setQuType(q.getQuType());
            questionFrom.setAnalysis(q.getAnalysis());

            List<Option> options = new ArrayList<>();

            // 前四个选项必须有
            if (isNotBlank(q.getOption1())) {
                options.add(createOption(q.getOption1(), q.getRighted1()));
            }
            if (isNotBlank(q.getOption2())) {
                options.add(createOption(q.getOption2(), q.getRighted2()));
            }
            if (isNotBlank(q.getOption3())) {
                options.add(createOption(q.getOption3(), q.getRighted3()));
            }
            if (isNotBlank(q.getOption4())) {
                options.add(createOption(q.getOption4(), q.getRighted4()));
            }

            // 选项5、6为可选项
            if (isNotBlank(q.getOption5())) {
                options.add(createOption(q.getOption5(), q.getRighted5()));
            }
            if (isNotBlank(q.getOption6())) {
                options.add(createOption(q.getOption6(), q.getRighted6()));
            }

            questionFrom.setOptions(options);
            list.add(questionFrom);
        }

        return list;
    }

    // 工具方法：创建选项对象
    private static Option createOption(String content, Integer isRight) {
        Option option = new Option();
        option.setContent(content.trim());
        option.setIsRight(isRight);
        return option;
    }

    // 工具方法：判断字符串是否非空
    private static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

}
