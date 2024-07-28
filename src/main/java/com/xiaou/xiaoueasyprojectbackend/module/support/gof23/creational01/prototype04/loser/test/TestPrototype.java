package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.prototype04.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.prototype04.loser.core.Answer;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.prototype04.loser.core.Paper;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.prototype04.loser.core.Question;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 推荐指数：★★★☆☆
 * 原型是一种创建型设计模式，使你能够复制已有对象，而又无需使代码依赖它们所属的类。
 * <p>
 * 模拟给不同的学生发考题
 */
public class TestPrototype {

    private static final Paper basePaper;

    static {
        basePaper = new Paper();
        Question questionA = new Question();
        questionA.setQuestion("哪个是A?");
        questionA.setAnswers(Arrays.asList(
                new Answer("A", "A", true),
                new Answer("B", "B", false),
                new Answer("C", "C", false),
                new Answer("D", "D", false)
        ));
        Question questionB = new Question();
        questionB.setQuestion("哪个比1小?");
        questionB.setAnswers(Arrays.asList(
                new Answer("A", "0", true),
                new Answer("B", "1", false),
                new Answer("C", "2", false),
                new Answer("D", "3", false)
        ));
        basePaper.setQuestions(Arrays.asList(questionA, questionB));
    }

    @Test
    public void test() {

        // 处理大对象的繁琐创建过程 注意深克隆 及 浅克隆
        Paper paperA = basePaper.clone();
        paperA.setStudent("大白");
        Paper paperB = basePaper.clone();
        paperB.setStudent("小白");
        Paper paperC = basePaper.clone();
        paperC.setStudent("loser");

        paperA.getQuestions().get(0).getAnswers().get(0).setAnswer("偷偷改题");
        Assert.assertNotEquals(
                paperA.getQuestions().get(0).getAnswers().get(0).getAnswer(),
                paperB.getQuestions().get(0).getAnswers().get(0).getAnswer()
        );

    }


}
