package com.sooa.study.day09;

import java.io.*;
import java.util.TreeMap;

public class Test3 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/a.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/aa.txt"), "utf-8"));
        String k;
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        while ((k = reader.readLine()) != null) {
            int kkk = Integer.parseInt(k.split(":")[0]);
            treeMap.put(kkk, k);
        }
        treeMap.forEach((integer, s) -> {
            try {
                writer.write(s);
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        reader.close();
        writer.close();
    }
}
