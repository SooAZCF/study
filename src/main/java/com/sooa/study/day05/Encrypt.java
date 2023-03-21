package com.sooa.study.day05;

import com.sooa.study.day05.util.Encryption;

import java.io.File;
import java.io.IOException;

public class Encrypt {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/a.txt");
        Encryption.encrypt(file, "MD5");
    }
}
