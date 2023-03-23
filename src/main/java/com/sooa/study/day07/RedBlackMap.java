package com.sooa.study.day07;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class RedBlackMap<K, V> {
    private transient Entry<K, V> root;
    private transient int size = 0;
    private Comparator<? super K> comparator;

    //    构造函数
    public RedBlackMap() {
        comparator = null;
    }

    public RedBlackMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }


    //    有key与value对应关联的put
    public V put(K key, V value) {
//        默认替换旧值
        return put(key, value, true);
    }

    //    没有key与value对应关联的put
    private V putIfAbsent(K key, V value) {
        return put(key, value, false);
    }

    private V put(K key, V value, boolean replaceOld) {
        Entry<K, V> temp = root;
//        存在根节点
        if (temp == null) {
            root = new Entry<>(key, value, null);
            size = 1;
            return value;
        }
//        存在已经存在的根节点
        Entry<K, V> temParent;//形参父节点
        int cmp;
//            调用者自己的比较器规则
        Comparator<? super K> comp = comparator;
        if (comparator != null) {
            do {
//            比较大小
                temParent = temp;
                cmp = comp.compare(key, temParent.key);
//            切换为左右子节点
                if (cmp < 0) temp = temp.left;
                else if (cmp > 0) temp = temp.right;
                else {
//                返回该key的旧value
                    V oldValue = temp.value;
                    if (replaceOld/*value是否可替代*/ || oldValue == null/*该key对用空value*/) {
                        temp.value = value;
                    }
                    return oldValue;
                }
            } while (temp != null);
        } else {
            do {
//            比较大小
                temParent = temp;
//                它自己的比较器
                Comparable<? super K> k = (Comparable<? super K>) key;
                cmp = k.compareTo(temParent.key);
//            切换为左右子节点
                if (cmp < 0) temp = temp.left;
                else if (cmp > 0) temp = temp.right;
                else {
//                返回该key的旧value
                    V oldValue = temp.value;
                    if (replaceOld/*value是否可替代*/ || oldValue == null/*该key对用空value*/) {
                        temp.value = value;
                    }
                    return oldValue;
                }
            } while (temp != null);
        }
//        存在不存在的根节点
        Entry<K, V> target = new Entry<>(key, value, temParent);
        if (cmp < 0) temParent.left = target;
        else temParent.right = target;
//        修正红黑颜色
        fixAfterInsertion(target);
        size++;
        return null;
    }

    //    红黑树核心逻辑
    private void fixAfterInsertion(Entry<K, V> target) {

    }

    //    左旋
    private void leftRotate(Entry<K, V> target) {

    }

    //    右旋
    private void rightRotate(Entry<K, V> target) {

    }


    public V get(K key) {
        Entry<K, V> temp = getEntry(key);
        return temp.getValue() == null ? null : temp.getValue();
    }

    private Entry<K, V> getEntry(K key) {
//        树枝遍历
        Comparable<? super K> keyy = (Comparable<? super K>) key;
        Entry<K, V> temp = root;
        while (root != null) {
            int k = keyy.compareTo(root.key);
            if (k < 0) temp = root.left;
            else if (k > 0) temp = root.right;
            else return root;
        }

        return null;
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
