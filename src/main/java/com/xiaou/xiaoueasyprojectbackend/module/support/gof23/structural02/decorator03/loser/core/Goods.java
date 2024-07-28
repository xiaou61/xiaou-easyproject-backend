package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.decorator03.loser.core;

import java.util.List;

public abstract class Goods implements Car {

    private final Car car;

    public Goods(Car car) {
        this.car = car;
    }

    @Override
    public int getPrice() {
        return car.getPrice();
    }

    @Override
    public List<String> getNames() {
        return car.getNames();
    }

}
