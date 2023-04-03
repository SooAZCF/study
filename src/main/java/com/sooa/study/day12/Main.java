package com.sooa.study.day12;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/info.txt");
        Info.generateInfo(4, 1, file.toString());

//        randomOutName(file);
//        targetOutName(file,"张三",3);
//        chanceOutName(file, 0.7, 0.3);
        randomOutNamePro(file);


    }

    private static void randomOutNamePro(File file) throws IOException {
        File file1 = new File("src/main/resources/infoed.txt");
        if (!file1.exists()) file1.createNewFile();
//        读入所有人
        ArrayList<String> nameList = getNameList(file);
        ArrayList<String> namedList = getNameList(file1);
        ArrayList<String> nameListCopy = nameList;

        if (compareArr(nameListCopy, namedList)) {//第二轮
            file1.delete();
            file1.createNewFile();
            namedList = getNameList(file1);
        }
//        随机
        nameList.removeAll(namedList);//删除重复的已点名对象
        Collections.shuffle(nameList);
//        点名一位
        String target = nameList.get(0);
        System.out.println(target.split("-")[0]);
        namedList.add(target);
//        持久化存储
        BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
        for (String s : namedList) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
//        在原文件名字后加点名次数/点名后放入新文件，每次取则比较
//        学生点名不重复/点完了自动开启第二轮
    }

    private static boolean compareArr(ArrayList<String> nameListCopy, ArrayList<String> namedList) {
//        地址相同
        if (nameListCopy == namedList) return true;
//        长度相同
        if (namedList.size() == nameListCopy.size()) return true;
        return false;
    }

    private static void chanceOutName(File file, double c1, double c2) throws IOException {
        ArrayList<String> strings = getNameList(file);
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
    }

    private static void targetOutName(File file, String name, int counts) throws IOException {
        File countFile = new File(file.getParentFile(), "count.txt");
        if (!countFile.exists()) countFile.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(countFile));
//        读取调用次数
//        readLine()方法在进行读取一行时，只有遇到回车(\r)或者换行符(\n)才会返回读取结果，这就是“读取一行的意思”，重要的是readLine()返回的读取内容中并不包含换行符或者回车符；
        String count = br.readLine();
        BufferedWriter bw = new BufferedWriter(new FileWriter(countFile));//new对象时会导致文件清空变为null
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
        ArrayList<String> nameList = getNameList(file);
        Collections.shuffle(nameList);
        System.out.println(nameList.get(0).split("-")[0]);
    }

    private static ArrayList<String> getNameList(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> strings = new ArrayList<>();
        String k;
        while ((k = br.readLine()) != null) {
            if (k == "" || k == "\\s") continue;
            strings.add(k);
        }
        br.close();
        return strings;
    }
}
