package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.imp.FreeGame;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.imp.VipGame;

/**
 * 该接口中声明了两个重载方法
 * 访问 FreeGame 的 visit 方法
 * 访问 VipGame 的 visit 方法
 * <p>
 * 在 Game 中调用 visit 方法 , 将自身传递到 visit 方法作为参数
 * 分别调用不同的重载方法
 * <p>
 * 访问者可以扩展很多了 , 可以定义若干实现了 IVisitor 接口的访问者
 * 每个 Visistor 访问者 , 访问不同的游戏 , 有不同的表现 , 如 :
 * 白嫖访问者 , 不花钱 , 只能玩免费游戏 , 不能玩收费游戏
 * 付费访问者 , 购买 VIP , 可以同时玩免费和收费游戏
 * <p>
 * 对于免费游戏来说 , 传入任何访问者都可以访问 ;
 * 对于收费游戏来说 , 传入付费访问者, 才可以访问 ;
 * <p>
 * 相同的 Visitor 对不同的数据产生不同的行为
 * 不同的 Visitor 对相同的数据产生不同的行为
 */
public interface IVisitor {

    /**
     * 访问免费游戏
     *
     * @param freeGame
     */
    void visit(FreeGame freeGame);

    /**
     * 访问收费游戏
     *
     * @param vipGame
     */
    void visit(VipGame vipGame);
}
