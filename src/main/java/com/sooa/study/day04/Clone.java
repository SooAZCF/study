package com.sooa.study.day04;

import java.io.*;

public class Clone {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("C:\\Users\\sooaz\\Desktop\\a.txt");
        OutputStream outputStream = new FileOutputStream("src/main/resources/aaa/a.txt");
        byte[] temp = new byte[8];
        int len = 0;
//        边读边写
        while ((len = inputStream.read(temp)) != -1) {//读入时返回读到的字节长度
            outputStream.write(temp, 0, len);//写出需要划定输入给的长度范围，否则会导致覆盖引用
        }
        inputStream.close();
        outputStream.close();

    }
}
