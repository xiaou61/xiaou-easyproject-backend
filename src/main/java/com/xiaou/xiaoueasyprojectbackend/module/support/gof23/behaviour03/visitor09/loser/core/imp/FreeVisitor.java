package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.imp;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.IVisitor;

public class FreeVisitor implements IVisitor {

    /**
     * 访问免费游戏 , 打印游戏名称
     *
     * @param freeGame
     */
    @Override
    public void visit(FreeGame freeGame) {
        System.out.println("我是普通用户 , 可以访问免费游戏 : " + freeGame.getGameName());
    }

    /**
     * 访问收费游戏 , 打印游戏名称和价格
     *
     * @param vipGame
     */
    @Override
    public void visit(VipGame vipGame) {
        System.err.println("我是普通用户 ,不可以访问收费游戏 : " + vipGame.getGameName() + " , 价格 : " + vipGame.getmVipPrice());
    }
}
