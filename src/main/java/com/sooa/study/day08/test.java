package com.sooa.study.day08;

public class test {
    public static void main(String[] args) {
        V v = new V();
        v.test(6);
    }

    static class V {
        void test(long t) {
            System.out.println("long");
        }

        void test(double t) {
            System.out.println("double");
        }

    }
}
