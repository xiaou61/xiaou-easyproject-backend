package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.decorator03.loser.biz;



import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.decorator03.loser.core.Car;
import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.decorator03.loser.core.Goods;

import java.util.List;

public class CarMat extends Goods {

    public CarMat(Car car) {
        super(car);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 200;
    }

    @Override
    public List<String> getNames() {
        List<String> names = super.getNames();
        names.add("全包车垫");
        return names;
    }

}
