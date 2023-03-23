package com.sooa.study.day06;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
//        q1();
        q2();
    }

    private static void q2() throws IOException {
        Reader reader = new FileReader("src/main/resources/a.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
    }

    private static void q1() throws IOException {
        InputStream inputStream = new FileInputStream("src/main/resources/a.txt");
        OutputStream outputStream = new FileOutputStream("src/main/resources/aaa/a.txt");
        BufferedInputStream ii = new BufferedInputStream(inputStream);
        BufferedOutputStream iii = new BufferedOutputStream(outputStream);
        int k;
        while ((k = inputStream.read()) != -1) {
            outputStream.write(k);
        }
        ii.close();
        iii.close();
    }
}
