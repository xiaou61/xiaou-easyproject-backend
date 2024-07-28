package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.singleton01.loser.test;

import cn.hutool.core.lang.ClassScanner;

import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.singleton01.loser.biz.CarService;

import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.singleton01.loser.biz.UserService;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.singleton01.loser.core.ApplicationContext;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 推荐指数：★★★★★
 * <p>
 * 单例是一种创建型设计模式，让你能够保证一个类只有一个实例，并提供一个访问实例的全局节点。
 * <p>
 * 模拟容器的单例池
 */
public class TestSingleton {

    public static final ExecutorService EXECUTOR_GENERAL = new ThreadPoolExecutor(100, 1000,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10000));

    @Test
    public void test1() throws Exception {
        // 饿汉 初始化类的时候奖需要的对象初始化
        synchronized (TestSingleton.class) {
            test(false);
        }
    }

    @Test
    public void test2() throws Exception {
        // 懒汉 调用的时候进行对象初始化
        synchronized (TestSingleton.class) {
            test(true);
        }
    }

    public void test(boolean isLazy) throws Exception {

        // 01 初始化容器
        Set<Class<?>> classes = ClassScanner.scanPackage("com.jingdianjichi.loser");
        ApplicationContext applicationContext = new ApplicationContext(classes, isLazy);

        System.out.println(isLazy + " " + applicationContext.getTargetMap().size());
        // 02 并发拿对象
        int size = 1000;
        List<UserService> userServices = new ArrayList<>(size);
        List<CarService> carServices = new ArrayList<>(size);
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < size; i++) {
            EXECUTOR_GENERAL.execute(() -> {
                try {
                    System.out.println("cur thread " + Thread.currentThread().getName());
                    UserService userService = applicationContext.getBean(UserService.class);
                    userServices.add(userService);
                    CarService carService = applicationContext.getBean(CarService.class);
                    carServices.add(carService);
                } catch (Exception ignore) {
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        boolean await = countDownLatch.await(5, TimeUnit.SECONDS);

        // 03 验证结果
        List<UserService> uList = userServices.stream().distinct().collect(Collectors.toList());
        List<CarService> cList = carServices.stream().distinct().collect(Collectors.toList());
        Assert.assertEquals(uList.size(), 1);
        Assert.assertEquals(cList.size(), 1);

    }

}
