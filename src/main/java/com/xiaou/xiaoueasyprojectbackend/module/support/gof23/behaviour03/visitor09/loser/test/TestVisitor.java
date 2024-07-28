package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.test;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.Game;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.imp.FreeGame;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.imp.FreeVisitor;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.imp.VipGame;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.imp.VipVisitor;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 推荐指数：★★★☆☆
 * <p>
 * 访问者是一种行为设计模式，它能将算法与其所作用的对象隔离开来。
 * <p>
 * 模拟用户访问游戏
 */
public class TestVisitor {

    @Test
    public void test() {

        // 创建存放 Game 的集合
        ArrayList<Game> games = new ArrayList<>();

        // 创建免费游戏
        FreeGame freeGame = new FreeGame();
        freeGame.setGameName("超级马里奥");
        games.add(freeGame);

        // 创建收费游戏
        VipGame vipGame = new VipGame();
        vipGame.setGameName("绝地求生");
        vipGame.setmVipPrice(88);
        games.add(vipGame);

        // Vip 访问者访问免费和收费游戏
        for (Game game : games) {
            game.accept(new VipVisitor());
        }

        for (Game game : games) {
            game.accept(new FreeVisitor());
        }

    }

}
