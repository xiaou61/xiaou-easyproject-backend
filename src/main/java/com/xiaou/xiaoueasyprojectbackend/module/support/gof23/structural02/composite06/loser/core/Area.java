package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.composite06.loser.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Area implements Counter {

    private String name;

    private long count;

    private final List<Counter> counters = new ArrayList<>();

    public void add(Counter counter) {
        counters.add(counter);
    }

    public Area(String name) {
        this.name = name;
    }

    public Area(String name, long count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public long count() {
        for (Counter counter : counters) {
            count += counter.count();
        }
        return count;
    }

}
