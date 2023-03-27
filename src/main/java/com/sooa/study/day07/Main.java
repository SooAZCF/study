package com.sooa.study.day07;

public class Main {
    public static void main(String[] args) {
        byte[] k = new byte[4];
        for (int i = 0; i < k.length; i++) {
            k[i] = 66;
        }
        System.out.println(new String(k));
    }
}
