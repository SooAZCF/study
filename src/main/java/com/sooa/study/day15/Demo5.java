package com.sooa.study.day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Demo5 {
    static ArrayList<String> bonusBox = new ArrayList<>();

    static {
        Collections.addAll(bonusBox, "10", "5", "20", "50", "100", "200", "500", "800", "2", "80", "300", "700");
    }

    public static void main(String[] args) {
        Box box1 = new Box("抽奖箱1");
        Box box2 = new Box("抽奖箱2");

        box1.start();
        box2.start();
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
                synchronized (Demo5.class) {
                    if (bonusBox.size() == 0) break;
                    int k = new Random().nextInt(bonusBox.size());
                    System.out.println(Thread.currentThread().getName() + "又产生了一个" + bonusBox.get(k) + "元大奖");
                    bonusBox.remove(k);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
