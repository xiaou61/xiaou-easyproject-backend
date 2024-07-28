package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.imp;


import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.Game;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.visitor09.loser.core.IVisitor;

public class VipGame extends Game {

    public int getmVipPrice() {
        return mVipPrice;
    }

    public void setmVipPrice(int mVipPrice) {
        this.mVipPrice = mVipPrice;
    }

    private int mVipPrice;

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
