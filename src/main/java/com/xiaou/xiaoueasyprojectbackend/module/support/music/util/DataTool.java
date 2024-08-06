package com.xiaou.xiaoueasyprojectbackend.module.support.music.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class DataTool {
    /**
     * 将原始的 list 按照 filter 过滤
     *
     * @param source 数据原始集合
     * @param filter 过滤规则
     * @param <T>    数据类型
     * @return list
     */
    public static <T> List<T> filter(List<T> source,
                                     Predicate<? super T> filter) {
        if (isEmpty(source)) {
            return Collections.emptyList();
        }

        return source.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public static <S, T> List<T> toList(List<S> source,
                                        Predicate<S> filter,
                                        Function<? super S, ? extends T> mapper) {
        return toList(source, filter, mapper, false);
    }

    /**
     * 将原始的 list 按照 filter 过滤之后，按照 mapper 规则转成新的 list
     *
     * @param source   源数据集合
     * @param filter   源数据集合过滤规则
     * @param mapper   生成新的 list 接口规则
     * @param distinct 是否去重 distinct 为 true，表示过滤之后生成list之前进行去重操作
     * @param <S>      源数据类型
     * @param <T>      目标数据类型
     * @return list
     */
    public static <S, T> List<T> toList(List<S> source,
                                        Predicate<S> filter,
                                        Function<? super S, ? extends T> mapper,
                                        boolean distinct) {
        if (isEmpty(source)) {
            return Collections.emptyList();
        }

        Stream<? extends T> stream = source.stream()
                .filter(filter::test)
                .map(mapper);

        if (distinct) {
            stream = stream.distinct();
        }

        return stream.collect(Collectors.toList());
    }

    public static <S, T> List<T> toList(List<S> source,
                                        Function<? super S, ? extends T> mapper) {
        return toList(source, mapper, false);
    }

    /**
     * 将原始的 list 按照 mapper 规则转成新的 list
     *
     * @param source   源数据集合
     * @param mapper   生成新的 list 接口规则
     * @param distinct 是否去重
     * @param <S>      源数据类型
     * @param <T>      目标数据类型
     * @return list
     */
    public static <S, T> List<T> toList(List<S> source,
                                        Function<? super S, ? extends T> mapper,
                                        boolean distinct) {
        if (isEmpty(source)) {
            return Collections.emptyList();
        }

        if (distinct) {
            return source.stream().map(mapper).distinct().collect(Collectors.toList());
        }

        return source.stream().map(mapper).collect(Collectors.toList());
    }


    public static <K, S> Map<K, S> toMap(List<S> source,
                                         Function<? super S, ? extends K> keyMapper) {
        return toMap(source, keyMapper, identity());
    }

    /**
     * list 转 map 集合
     *
     * @param source      数据源集合
     * @param keyMapper   map 集合中的 key 获取规则
     * @param valueMapper map 集合中的 value 获取规则
     * @param <K>         map 集合中的 key 类型
     * @param <S>         数据源集合中元素的类型
     * @param <T>         map 集合中的 value 类型
     * @return map 集合
     */
    public static <K, S, T> Map<K, T> toMap(List<S> source,
                                            Function<? super S, ? extends K> keyMapper,
                                            Function<? super S, ? extends T> valueMapper) {
        if (isEmpty(source)) {
            return Collections.emptyMap();
        }

        return source.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }


    /**
     * 枚举转 map 集合
     *
     * @param sourceEnumClass 数据源枚举 Class类
     * @param keyMapper       map 集合中的 key 获取规则
     * @param <K>             map 集合中的 key 类型
     * @param <T>             map 集合中的 value 类型
     * @return map 集合
     */
    public static <K, T extends Enum<T>> Map<K, T> enumMap(Class<T> sourceEnumClass,
                                                           Function<T, K> keyMapper) {
        EnumSet<T> enumSet = EnumSet.allOf(sourceEnumClass);
        return enumSet.stream().collect(Collectors.toMap(keyMapper, Function.identity()));
    }

    /**
     * 枚举转 map 集合
     * map 集合中的 key 为 Enum 的 name() 方法返回值
     *
     * @param sourceEnumClass 数据源枚举 Class类
     * @param <T>             map 集合中的 value 类型
     * @return map 集合
     */
    public static <T extends Enum<T>> Map<String, T> enumMap(Class<T> sourceEnumClass) {
        EnumSet<T> enumSet = EnumSet.allOf(sourceEnumClass);
        return enumSet.stream().collect(Collectors.toMap(Enum::name, Function.identity()));
    }


    /**
     * 将 list 集合分组
     *
     * @param source     数据源集合
     * @param classifier 分组规则
     * @param <K>        map 集合中的 key 类型
     * @param <S>        map 集合中的 value 类型
     * @return list
     */
    public static <K, S> Map<K, List<S>> groupingBy(List<S> source,
                                                    Function<? super S, ? extends K> classifier) {
        if (isEmpty(source)) {
            return Collections.emptyMap();
        }

        return source.stream().collect(Collectors.groupingBy(classifier));
    }

    /**
     * 将过滤的 list 集合分组
     *
     * @param source     数据源集合
     * @param filter     过滤源数据集合的接口规则
     * @param classifier 分组规则
     * @param <K>        map 集合中的 key 类型
     * @param <S>        map 集合中的 value 类型
     * @return list
     */
    public static <K, S> Map<K, List<S>> groupingBy(List<S> source,
                                                    Predicate<S> filter,
                                                    Function<? super S, ? extends K> classifier) {
        if (isEmpty(source)) {
            return Collections.emptyMap();
        }

        return source.stream()
                .filter(filter::test)
                .collect(Collectors.groupingBy(classifier));
    }



}
