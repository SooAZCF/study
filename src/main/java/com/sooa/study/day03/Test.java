package com.sooa.study.day03;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileInputStream = new FileOutputStream("src/main/resources/a.txt", true);
        fileInputStream.write('\n');
        fileInputStream.write('b');


        fileInputStream.close();


    }
}
