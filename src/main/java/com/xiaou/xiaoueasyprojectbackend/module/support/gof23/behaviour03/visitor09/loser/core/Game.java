package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core;

/**
 * 游戏父类 , 所有的游戏都要继承该类
 * VipGame 和 FreeGame 都继承 Game , 这两个都是 Game
 */
public abstract class Game {
    /**
     * 游戏名称
     */
    private String mGameName;

    public String getGameName() {
        return mGameName;
    }

    public void setGameName(String gameName) {
        this.mGameName = gameName;
    }

    /**
     * 当有访问者要访问游戏时 , 将访问者传入该方法
     * 用于判定访问者是否有权限访问游戏
     *
     * @param visitor
     */
    public abstract void accept(IVisitor visitor);
}
