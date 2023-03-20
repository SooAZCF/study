package com.sooa.study.day04;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws IOException {
        String ss = "jkdakfjkkjhg\r\nksjdfkahskjgjk";
        File file = new File("src/main/resources/a.txt");
        InputStream inputStream = new FileInputStream(file);
        int k;
        while ((k = inputStream.read()) != -1) {
            System.out.print((char) k);
        }
        System.out.println();
        inputStream.close();
    }
}
