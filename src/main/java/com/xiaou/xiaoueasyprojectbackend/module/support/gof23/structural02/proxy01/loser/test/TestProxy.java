package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.biz.SysUser;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.biz.SysUserDao;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.proxy01.loser.core.DaoFactory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 推荐指数：★★★★★
 * <p>
 * 代理是一种结构型设计模式，让你能够提供对象的替代品或其占位符。代理控制着对于原对象的访问，并允许在将请求提交给对象前后进行一些处理。
 * <p>
 * 模拟mybatis给dao生成代理类
 */
public class TestProxy {

    @Test
    public void test() {

        DaoFactory factory = new DaoFactory();
        SysUserDao sysUserDao = factory.getData(SysUserDao.class);
        Assert.assertNotNull(sysUserDao);
        Assert.assertTrue(sysUserDao instanceof Proxy);
        SysUser sysUser = sysUserDao.getById(1L);
        Assert.assertNotNull(sysUser);

    }
}
