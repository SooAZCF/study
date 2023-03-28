package com.sooa.study.day08;

public class test {
    public static void main(String[] args) {
        V v = new V();
        v.test(6);
    }

    static class V {
        void test(long t) {
            System.out.println("l 撒大大g");
        }

        void test(double t) {
            System.out.println("double");
        }

    }
}
