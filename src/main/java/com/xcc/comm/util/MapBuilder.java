package com.xcc.comm.util;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {

    private final Map<K, V> innerMap;

    /**
     * 创建新实例
     *
     * @return
     */
    public static <K, V> MapBuilder<K, V> newInstance() {
        return new MapBuilder<>();
    }

    /**
     * 创建新实例
     *
     * @param initialCapacity
     *            初始容量
     * @return
     */
    public static <K, V> MapBuilder<K, V> newInstance(int initialCapacity) {
        return new MapBuilder<>(initialCapacity);
    }

    public MapBuilder() {
        this.innerMap = new HashMap<>(16);
    }

    /**
     * 构造器
     *
     * @param initialCapacity
     *            初始容量
     */
    public MapBuilder(int initialCapacity) {
        this.innerMap = new HashMap<>(initialCapacity);
    }

    /**
     * 添加元素
     *
     * @param key
     * @param value
     * @return
     */
    public MapBuilder<K, V> put(K key, V value) {
        this.innerMap.put(key, value);
        return this;
    }

    /**
     * 不存在则添加元素
     *
     * @param key
     * @param value
     * @return
     */
    public MapBuilder<K, V> putIfAbsent(K key, V value) {
        this.innerMap.putIfAbsent(key, value);
        return this;
    }

    /**
     * 添加多个元素
     *
     * @param map
     * @return
     */
    public MapBuilder<K, V> putAll(Map<K, V> map) {
        if (MapUtils.isNotEmpty(map)) {
            this.innerMap.putAll(map);
        }
        return this;
    }

    /**
     * 删除元素
     *
     * @param key
     * @return
     */
    public MapBuilder<K, V> remove(K key) {
        this.innerMap.remove(key);
        return this;
    }

    /**
     * 构造map
     *
     * @return
     */
    public Map<K, V> build() {
        return this.innerMap;
    }

}
