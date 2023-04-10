package com.sooa.study.day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Demo7 {
    static ArrayList<String> bonusBox = new ArrayList<>();
    static ArrayList<String> strings = new ArrayList<>();

    static {
        Collections.addAll(bonusBox, "10", "5", "20", "50", "100", "200", "500", "800", "2", "80", "300", "700");
    }

    public static void main(String[] args) throws InterruptedException {
        Box box1 = new Box("抽奖箱1");
        Box box2 = new Box("抽奖箱2");

        MyThread t1 = new MyThread();
        box1.start();
        box2.start();
        box1.join();
        box2.join();
        t1.start();


    }

    static class Box extends Thread {
        public Box() {
        }

        public Box(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                synchronized (Demo7.class) {
                    if (bonusBox.size() == 0) break;
                    int k = new Random().nextInt(bonusBox.size());
                    strings.add(bonusBox.get(k) + "-" + Thread.currentThread().getName());
                    bonusBox.remove(k);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    static class MyThread extends Thread {
        public MyThread() {
        }

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            Iterator<String> it = strings.iterator();
//        总数
            int c1Num = 0;
            int c2Num = 0;
//        最大值
            int c1Max = 0;
            int c2Max = 0;
//        总和
            int c1Sum = 0;
            int c2Sum = 0;
//        分别为
            String c1String = "";
            String c2String = "";
            while (it.hasNext()) {
                String s = it.next();
                String[] ss = s.split("-");
                if ("抽奖箱1".equals(ss[1])) {
                    c1String += ss[0] + ",";
                    c1Num++;
                    c1Sum += Integer.parseInt(ss[0]);
                    if (c1Max < Integer.parseInt(ss[0])) c1Max = Integer.parseInt(ss[0]);
                }
                if ("抽奖箱2".equals(ss[1])) {
                    c2String += ss[0] + ",";
                    c2Num++;
                    c2Sum += Integer.parseInt(ss[0]);
                    if (c2Max < Integer.parseInt(ss[0])) c2Max = Integer.parseInt(ss[0]);
                }

            }
            System.out.println("此次抽奖中，抽奖箱1总共产生了" + c1Num + "个奖项");
            System.out.println("分别为" + c1String + "最高奖项为" + c1Max + "元，总额为" + c1Sum);
            System.out.println("此次抽奖中，抽奖箱2总共产生了" + c2Num + "个奖项");
            System.out.println("分别为" + c2String + "最高奖项为" + c2Max + "元，总额为" + c2Sum);
            if (c1Max > c2Max) System.out.println("此次抽奖中，抽奖箱1中产生了最大奖项为" + c1Max);
            if (c1Max < c2Max) System.out.println("此次抽奖中，抽奖箱2中产生了最大奖项为" + c2Max);
        }
    }
}
