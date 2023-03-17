package com.sooa.study.day03;

import java.util.Comparator;

public class SSS {
    public static void main(String[] args) {
        Comparator cc = (o1, o2) -> o1.hashCode() - o2.hashCode();
        Comparator<String> ccc = (o1, o2) -> o1.compareTo(o2);
        Comparator<String> cccc = String::compareTo;
    }
}
