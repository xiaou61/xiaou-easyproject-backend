package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.memento10.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.memento10.loser.core.GameRole;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.memento10.loser.core.RoleStateCaretaker;
import org.junit.Test;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 备忘录是一种行为设计模式，允许在不暴露对象实现细节的情况下保存和恢复对象之前的状态。
 * <p>
 * 模拟游戏存档
 */
public class TestMemento {

    @Test
    public void test() {

        System.out.println("---------------大战boos前-----------------");
        //创建游戏角色对象
        GameRole gameRole = new GameRole();
        gameRole.initState();//初始化状态操作
        gameRole.stateDisplay();

        //将该游戏角色内部状态进行备份
        //创建管理者对象
        RoleStateCaretaker roleStateCaretaker = new RoleStateCaretaker();
        roleStateCaretaker.setRoleStateMemento(gameRole.saveState());

        System.out.println("---------------大战boos后-----------------");
        //损耗严重
        gameRole.fight();
        gameRole.stateDisplay();

        System.out.println("---------------恢复之前的状态-----------------");
        gameRole.recoverState(roleStateCaretaker.getRoleStateMemento());
        gameRole.stateDisplay();
    }

}
