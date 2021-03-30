package com.kayu.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Jin.Z.J  2021/3/29
 */

public class MapUtils {


    /**
     * map排序 key desc
     *
     * @param map
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> sort(Map<String, T> map) {
        return sort(map, String::compareTo);
    }

    /**
     * map排序
     *
     * @param map
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> sort(Map<String, T> map, Comparator<String> comparator) {
        if (map instanceof TreeMap) {
            return map;
        }
        TreeMap<String, T> treeMap = newTreeMap(comparator);
        treeMap.putAll(map);
        return treeMap;
    }

    /**
     * 排序并拼接
     *
     * @param map
     * @param separator
     * @param kvSeparator
     * @return
     */
    public static String sortJoin(Map<String, Object> map, String separator, String kvSeparator) {
        return join(sort(map), separator, kvSeparator, true);
    }

    /**
     * 排序并拼接
     *
     * @param map
     * @param separator
     * @param kvSeparator
     * @return
     */
    public static String sortJoin(Map<String, Object> map, String separator, String kvSeparator, boolean ignoreNull) {
        return join(sort(map), separator, kvSeparator, ignoreNull);
    }

    /**
     * 拼接
     *
     * @param map
     * @param separator
     * @param kvSeparator
     * @return
     */
    public static String join(Map<String, Object> map, String separator, String kvSeparator) {
        return join(map, separator, kvSeparator, true);
    }


    /***
     * 拼接
     * @param map
     * @param separator
     * @param kvSeparator
     * @param ignoreNull
     * @return
     */
    public static String join(Map<String, Object> map, String separator, String kvSeparator, boolean ignoreNull) {
        StringBuilder builder = new StringBuilder();
        map.forEach((k, v) -> {
            if (ignoreNull && v == null) {
                return;
            }
            builder.append(separator).append(k).append(kvSeparator).append(v == null ? "" : v.toString());
        });
        return builder.substring(1, builder.length());
    }

    /**
     * 是否为空|无元素
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }


    /**
     * 是否不为空
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }


    /**
     * 创建 HashMap
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }


    /**
     * 创建 TreeMap
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> TreeMap<K,V> newTreeMap(){
        return newTreeMap(null);
    }

    /**
     * 创建 TreeMap
     * @param comparator
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> TreeMap<K,V> newTreeMap(Comparator<K> comparator){
        return new TreeMap<>(comparator);
    }


}
