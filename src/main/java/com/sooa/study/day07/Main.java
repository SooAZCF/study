package com.sooa.study.day07;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        RedBlackMap<String, String> k = new RedBlackMap<>();
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/as.txt"));
        outputStream.writeObject(k);

    }
}
