package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.iterator08.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.iterator08.loser.core.AarrayIterator;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.iterator08.loser.core.Iterator;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 迭代器是一种行为设计模式，让你能在不暴露集合底层表现形式（列表、栈和树等）的情况下遍历集合中所有的元素。
 * <p>
 * 模拟自定义实现数组集合
 */
public class TestIterator {

    @Test
    public void test() {

        AarrayIterator<Integer> list = new AarrayIterator<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("next = " + next);
        }

    }

}
