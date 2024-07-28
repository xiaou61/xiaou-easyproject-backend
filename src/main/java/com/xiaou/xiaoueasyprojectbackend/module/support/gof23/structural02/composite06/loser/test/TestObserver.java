package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.composite06.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.composite06.loser.core.Area;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 组合是一种结构型设计模式，你可以使用它将对象组合成树状结构，并且能像使用独立对象一样使用它们。
 * <p>
 * 模拟人口统计
 */
public class TestObserver {

    @Test
    public void test() {

        Area zg = new Area("中国");

        Area gd = new Area("广东省");

        Area gz = new Area("广东省-广州市");

        Area th = new Area("广东省-广州市-天河区");
        Area ld = new Area("广东省-广州市-天河区-猎德", 1000);
        Area zjxc = new Area("广东省-广州市-天河区-珠江新城", 1000);
        Area by = new Area("广东省-广州市-白云区", 2000);
        Area py = new Area("广东省-广州市-番禺区", 1500);

        zg.add(gd);

        gd.add(gz);

        gz.add(th);
        gz.add(by);
        gz.add(py);

        th.add(ld);
        th.add(zjxc);

        System.out.println(zg.count());

    }

}
