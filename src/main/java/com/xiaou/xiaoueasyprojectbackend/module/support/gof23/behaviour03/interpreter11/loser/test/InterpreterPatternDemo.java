package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.interpreter11.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.interpreter11.loser.core.AndExpression;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.interpreter11.loser.core.OrExpression;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.interpreter11.loser.core.TerminalExpression;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 解释器模式给定一个语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子。
 * <p>
 * 模拟断言解析器
 */
public class InterpreterPatternDemo {

    @Test
    public void test() {

        AndExpression and1 = new AndExpression(new TerminalExpression("1"), new TerminalExpression("2"));
        AndExpression and2 = new AndExpression(new TerminalExpression("1"), new TerminalExpression("1"));
        OrExpression or1 = new OrExpression(new TerminalExpression("1"), new TerminalExpression("2"));
        OrExpression or2 = new OrExpression(new TerminalExpression("2"), new TerminalExpression("1"));
        OrExpression or3 = new OrExpression(new TerminalExpression("1"), new TerminalExpression("1"));
        OrExpression or4 = new OrExpression(new TerminalExpression("2"), new TerminalExpression("2"));

        System.out.println("两个都是 1 " + and1.interpret("1"));
        System.out.println("两个都是 1 " + and2.interpret("1"));
        System.out.println("一个是 1 " + or1.interpret("1"));
        System.out.println("一个是 1 " + or2.interpret("1"));
        System.out.println("一个是 1 " + or3.interpret("1"));
        System.out.println("一个是 1 " + or4.interpret("1"));
    }

}