package com.sooa.study.day15;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class sd {
    public static void main(String[] args) {
        String emailRegex = "\\w+@\\w+\\.com";
        System.out.println(new Scanner(System.in, StandardCharsets.UTF_8).next().matches(emailRegex));
    }
}
