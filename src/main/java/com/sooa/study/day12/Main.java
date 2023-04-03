package com.sooa.study.day12;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/info.txt");
//        Info.generateInfo(50, 50, file.toString());

//        randomOutName(file);
//        targetOutName(file,"张三",3);
//        chanceOutName(file, 0.7, 0.3);
        randomOutNamePro(file);
    }

    private static void randomOutNamePro(File file) {
    }

    private static void chanceOutName(File file, double c1, double c2) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> strings = new ArrayList<>();
        String k;
        while ((k = br.readLine()) != null) {
            if (k == "" || k == "\\s") continue;
            strings.add(k);
        }
        Collections.shuffle(strings);
//        寻找男女
        String boy = null;
        String girl = null;
        for (int i = 0; i < strings.size(); i++) {
            if (boy != null && girl != null) break;
            String s = strings.get(i);
            if ("男".equals(s.split("-")[1])) boy = s.split("-")[0];
            else girl = s.split("-")[0];
        }
//        权值分配
        int chance = new Random().nextInt((int) ((c1 + c2) * 100)) + 1;
        if (0 < chance && chance < 71) {
            System.out.println(boy);
        } else {
            System.out.println(girl);
        }

        br.close();
    }

    private static void targetOutName(File file, String name, int counts) throws IOException {
        File countFile = new File(file.getParentFile(), "count.txt");
        if (!countFile.exists()) countFile.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(countFile));
//        读取调用次数
//        readLine()方法在进行读取一行时，只有遇到回车(\r)或者换行符(\n)才会返回读取结果，这就是“读取一行的意思”，重要的是readLine()返回的读取内容中并不包含换行符或者回车符；
        String count = br.readLine();

        BufferedWriter bw = new BufferedWriter(new FileWriter(countFile));
        if (count == null) {
            bw.write("1");
            bw.newLine();
            br.close();
            bw.close();
            return;
        }
//        判断调用次数
//        第三次必定张三
        if (Integer.parseInt(count) == counts) {
            System.out.println(name);
//        存储调用次数
            bw.write(String.valueOf(Integer.parseInt(count) + 1));
            bw.newLine();
//        关流
            br.close();
            bw.close();
            return;
        }

        randomOutName(file);
//        存储调用次数
        bw.write(String.valueOf(Integer.parseInt(count) + 1));
//        关流
        br.close();
        bw.close();
    }

    private static void randomOutName(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> strings = new ArrayList<>();
        String k;
        while ((k = br.readLine()) != null) {
            if (k == "" || k == "\\s") continue;
            strings.add(k);
        }
        Collections.shuffle(strings);
        System.out.println(strings.get(0).split("-")[0]);
        br.close();
    }
}
