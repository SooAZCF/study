package com.sooa.study.day03;

public class TTT {
    public static void main(String[] args) {
        System.out.println("exe1");
        try {
            System.out.println("exe1.5");
            String[] k = null;
            System.out.println(k[0]);
            System.out.println("exe1.8");
            System.out.println(k[1]);
            System.out.println("exe2");//不执行
        } catch (NullPointerException e) {
            e.fillInStackTrace();
        }
        System.out.println("exe3");
    }
}
