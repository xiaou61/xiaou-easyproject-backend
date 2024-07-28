package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.builder05.loser.core;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cache<K, V> {

    private String name;

    private int size;

    private Map<K, V> cache;

    public V get(K t) {
        return cache.get(t);
    }

    public V put(K k, V v) {
        return cache.put(k, v);
    }

    private Cache() {
    }

    public Cache(MyCacheBuilder<? super K, ? super V> cacheBuilder) {
        name = cacheBuilder.getName();
        size = cacheBuilder.getSize();
        cache = new HashMap<>(cacheBuilder.getSize());
    }

}
