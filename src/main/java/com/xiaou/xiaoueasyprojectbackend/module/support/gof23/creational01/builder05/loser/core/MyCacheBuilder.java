package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.builder05.loser.core;

public class MyCacheBuilder<K, V> {

    private int size;

    private String name;

    public static MyCacheBuilder<Object, Object> newBuilder() {
        return new MyCacheBuilder();
    }

    public MyCacheBuilder<K, V> name(String name) {
        this.name = name;
        return this;
    }

    public MyCacheBuilder<K, V> size(int size) {
        this.size = size;
        return this;
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        return new Cache<>(this);
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
