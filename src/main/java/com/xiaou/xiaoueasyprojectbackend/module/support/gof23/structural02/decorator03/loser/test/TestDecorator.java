package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.decorator03.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.decorator03.loser.biz.Camera;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.decorator03.loser.biz.CarMat;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.decorator03.loser.biz.YingBaoCar;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 装饰是一种结构型设计模式，允许你通过将对象放入包含行为的特殊封装对象中来为原对象绑定新的行为。
 * <p>
 * 模拟销售单添加选装件
 */
public class TestDecorator {

    @Test
    public void test() {

        // 模拟汽车报价单
        YingBaoCar car = new YingBaoCar();
        System.out.println(car.getNames());
        System.out.println(car.getPrice());
        System.out.println();

        // 加行车记录仪
        Camera camera = new Camera(car);
        System.out.println(camera.getNames());
        System.out.println(camera.getPrice());
        System.out.println();

        // 加全包车垫
        CarMat carMat = new CarMat(camera);
        System.out.println(carMat.getNames());
        System.out.println(carMat.getPrice());

    }


}
