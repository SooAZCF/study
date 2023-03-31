package com.sooa.study.day11;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] k = new int[]{9, 5, 1, 4, 8, 6, 5, 5};
        bubble(k);//冒泡排序 首先排出最大，然后次大，所以内层循环可以少一次遍历
        insert(k);//插入排序 无序范围更大遍历在外，选出值放入有序范围内比较
        choose(k);//选择排序 选择首位逐个比出最小给首位，再次之
        shell(k);//希尔排序 除以指定增量来划分小组，小组间对应值组成的数组进行插入排序
//        archive(k);
        quick(k, 0, k.length - 1);//快速排序 从左向右且从右向左，循环出与基值相比大且小的值下标，之间交换后再将基值移动，迭代（记得出口

        System.out.println(Arrays.toString(k));
    }

    private static void quick(int[] k, int left, int right) {
        if (left >= right) return;//出口

        int l = left, r = right;
        int pivot = k[left];

        while (l != r) {
//        从右向左找比基准小的
            while (true) {
                if (l >= r || k[r] < pivot) break;
                r--;
            }
//        从左向右找比基准大的
            while (true) {
                if (l >= r || k[l] > pivot) break;
                l++;
            }
//            交换左右
            swap(k, r, l);
        }
//        交换基准数
        swap(k, l, left);

        quick(k, left, l - 1);
        quick(k, l + 1, right);
    }

    private static void archive(int[] k) {
    }

    private static void shell(int[] k) {
        for (int minGroupLength = k.length / 2; minGroupLength > 0; minGroupLength /= 2) {//根据增量划分遍历小组
            for (int i = 0; i < minGroupLength; i++) {//遍历小组与下一小组对应值进行比较
//             ----插入排序------------------
                for (int j = minGroupLength + i; j < k.length; j += minGroupLength) {//小组间对应值组成的小组
                    for (int i1 = i; i1 < j; i1 += minGroupLength) {
                        if (k[i1] > k[j]) swap(k, i1, j);
                    }
                }
//            ---------------------------
            }
        }
    }

    private static void choose(int[] k) {
        int count = 0;
        for (int i = 0; i < k.length; i++) {
            for (int i1 = i + 1; i1 < k.length; i1++) {
                if (k[i1] < k[i]) swap(k, i, i1);
                count++;
            }
        }
        System.out.println(count);
    }

    private static void insert(int[] k) {
        int count = 0;
        for (int i = 1; i < k.length; i++) {//无序
            for (int i1 = 0; i1 < i; i1++) {//有序
                if (k[i1] > k[i]) swap(k, i1, i);
                count++;
            }
        }
        System.out.println(count);
    }

    private static void bubble(int[] k) {
        int count = 0;
        for (int i = 0; i < k.length; i++) {
            for (int i1 = 0; i1 < k.length - 1 - i; i1++) {
                if (k[i1] > k[i1 + 1]) swap(k, i1, i1 + 1);
                count++;
            }
        }
        System.out.println(count);
    }

    private static void swap(int[] k, int i1, int i2) {
        int temp = k[i1];
        k[i1] = k[i2];
        k[i2] = temp;
    }
}
