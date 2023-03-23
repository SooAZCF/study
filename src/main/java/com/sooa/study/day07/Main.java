package com.sooa.study.day07;

public class Main {
    public static void main(String[] args) {
        RedBlackMap<String, String> k = new RedBlackMap<>();
        k.put("sss", "sst");
        k.put("ttt", "ssss");
        System.out.println(k.get("sss"));
    }
}
