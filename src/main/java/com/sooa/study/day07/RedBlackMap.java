package com.sooa.study.day07;

import java.util.Map;
import java.util.Objects;

public class RedBlackMap<K, V> {
    private transient Entry<K, V> root;
    private transient int size = 0;

    public RedBlackMap(K key, V value) {

    }

    //    put存值
    public V put(K key, V value) {
//        默认替换旧值
        return put(key, value, true);
    }

    private V put(K key, V value, boolean replaceOld) {
        Entry<K, V> temp = root;
//        判断根节点是否有值
        if (temp == null) {
            root = new Entry<>(key, value, null);
            size = 1;
            return null;
        }
//
        return value;
    }

    public V get(K key) {
        Entry<K, V> temp = getEntry(key);
        return temp.getValue() == null ? null : temp.getValue();
    }

    private Entry<K, V> getEntry(K key) {

    }

    //    内部红黑树节点
    static final class Entry<K, V> implements Map.Entry<K, V> {
        private static final boolean BLACK = true;
        private static final boolean RED = false;
        K key;
        V value;

        Entry<K, V> parent;
        Entry<K, V> left;
        Entry<K, V> right;

        boolean color = BLACK;

        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> that = (Entry<?, ?>) o;
            return color == that.color && Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(parent, that.parent) && Objects.equals(left, that.left) && Objects.equals(right, that.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, parent, left, right, color);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
