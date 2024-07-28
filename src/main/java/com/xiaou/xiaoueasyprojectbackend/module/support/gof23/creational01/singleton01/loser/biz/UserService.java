package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.singleton01.loser.biz;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.singleton01.loser.core.Component;

/**
 * 模拟业务bean
 */
@Component
public class UserService {

    public void save() {
        System.out.println("save a user");
    }

}
