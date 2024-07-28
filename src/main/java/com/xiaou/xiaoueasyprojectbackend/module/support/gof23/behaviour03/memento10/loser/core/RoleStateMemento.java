package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.memento10.loser.core;

import lombok.Data;

@Data
public class RoleStateMemento {

    private int vit; //生命力
    private int atk; //攻击力
    private int def; //防御力

    public RoleStateMemento(int vit, int atk, int def) {
        this.vit = vit;
        this.atk = atk;
        this.def = def;
    }

}
