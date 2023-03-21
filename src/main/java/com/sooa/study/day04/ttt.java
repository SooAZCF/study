package com.sooa.study.day04;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ttt {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("src/main/resources/a.txt");
        int length;
        char[] chars = new char[8];
        while ((length = reader.read(chars)) != -1) {
            System.out.print(new String(chars, 0, length));
        }
        reader.close();
        System.out.println();
    }
}
