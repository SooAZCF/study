package com.sooa.study.day09;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/新建文本文档.txt");
        File fil1 = new File("src/main/resources/a.txt");
//        encode(file, fil1);
        dencode(fil1, file);
    }

    private static void dencode(File fil1, File file) throws IOException {
        InputStream inputStream = new FileInputStream(fil1);
        OutputStream outputStream = new FileOutputStream(file);
        int k;
        while ((k = inputStream.read()) != -1) {
            k--;
            outputStream.write(k);
        }
        inputStream.close();
        outputStream.close();
    }

    private static void encode(File file, File fil1) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new FileOutputStream(fil1);
        int k;
        while ((k = inputStream.read()) != -1) {
            k++;
            outputStream.write(k);
        }
        inputStream.close();
        outputStream.close();
    }
}
