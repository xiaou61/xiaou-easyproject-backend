package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.state05.loser.biz.Task;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 状态是一种行为设计模式，让你能在一个对象的内部状态变化时改变其行为，使其看上去就像改变了自身所属的类一样。
 * <p>
 * 模拟工单状态流转
 */
public class TestState {

    @Test
    public void test() throws Exception {

        synchronized (TestState.class) {
            Task task0 = new Task("0");
            task0.create();
            task0.start();
            task0.finish();
            Thread.sleep(1000L);
            System.out.println();
        }

        synchronized (TestState.class) {
            Task task1 = new Task("1");
            task1.create();
            task1.create();
            Thread.sleep(1000L);
            System.out.println();
        }

        synchronized (TestState.class) {
            Task task2 = new Task("2");
            task2.start();
            task2.finish();
            Thread.sleep(1000L);
            System.out.println();
        }

        synchronized (TestState.class) {
            Task task3 = new Task("3");
            task3.finish();
            task3.start();
            task3.create();
            Thread.sleep(1000L);
            System.out.println();
        }


        synchronized (TestState.class) {
            Task task4 = new Task("4");
            task4.create();
            task4.finish();
            task4.start();
            Thread.sleep(1000L);
            System.out.println();
        }

    }

}
