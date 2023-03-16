package com.sooa.study.day02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
//        q1();
        q2();
//        q3();
//        q4();

    }

    private static void q4() {
        File file = new File("src/main/resources/aaa");
        delete(file);
    }

    private static void delete(File file) {
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile()) file1.delete();
            if (file1.isDirectory()) delete(file1);
            file1.delete();
        }
    }

    private static void q3() {
        File file = new File("src/main/resources");
        if (check(file, ".txt") != null) {
            System.out.println(check(file, ".txt").length);
        } else System.out.println("null");

    }

    private static void q2() {
        File file = new File("src/main/resources/");
//        System.out.println(Arrays.toString(checkAll(file)));
        checkUnique(file, ".avi");
    }

    private static void checkUnique(File file, String allow) {
        File[] k = file.listFiles();
        for (File file1 : k) {
            if (file1.isFile()) if (file1.getName().endsWith(allow)) System.out.println(file1);
            if (file1.isDirectory()) checkUnique(file1, allow);
        }
    }

    private static String[] checkAll(File file) {
//        结果存放
        List<String> strings = new ArrayList<>();
//        文件判断
        if (file.isFile()) return null;
//        非空判断
//        当前目录下检测所有已存在avi文件
        String[] k = check(file, ".avi");
        if (k != null) Collections.addAll(strings, k);
//        检测下一级目录
        String[] files = file.list();
        for (String s : files) {
            File file1 = new File(file, s);
//            递归
            if (file1.isDirectory()) {
                Collections.addAll(strings, checkAll(file1));
            }
        }


        return strings.toArray(String[]::new);
    }

    private static String[] check(File file, String sth) {
        if (file.isFile()) return null;
        File[] k = file.listFiles(((dir, name) -> name.endsWith(sth)));
        if (k.length == 0) return null;
        return Arrays.stream(k).map(file1 -> file1.getName()).toArray(String[]::new);
    }

    private static void q1() throws IOException {
        File file = new File("src/main/resources/aaa/");
        System.out.println(file.mkdir());
//        file.createNewFile();
        System.out.println(new File(file, "a.txt").createNewFile());
    }
}
