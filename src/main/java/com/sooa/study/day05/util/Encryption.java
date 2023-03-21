package com.sooa.study.day05.util;

import java.io.*;

public class Encryption {


    public static char encrypteToChar(char key) {
        key = (char) (key * 10);
        return key;
    }

    public static void encrypt(File file, String encrypteMethod) throws IOException {
        if (file.isFile()) {
            switch (encrypteMethod) {
                case "MD5" -> {

                }
                default -> System.out.println("错误的加密方式");
            }

        } else System.out.println("输入路径非文件夹！");
    }

    ;
}
