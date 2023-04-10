package com.sooa.study.day15;

import java.util.ArrayList;
import java.util.Collections;

public class Demo4 {
    static ArrayList<Integer> s = new ArrayList<>();

    static {
        Collections.addAll(s, 44, 16, 40);
    }

    public static void main(String[] args) {

        Person p1 = new Person("1号");
        Person p2 = new Person("2号");
        Person p3 = new Person("3号");
        Person p4 = new Person("4号");
        Person p5 = new Person("5号");

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

    }

    static class Person extends Thread {
        public Person() {
        }

        public Person(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (Demo4.class) {
                if (s.size() != 0) {
                    System.out.println(Thread.currentThread().getName() + "抢到了" + s.get(0) + "元");
                    s.remove(0);
                } else System.out.println(Thread.currentThread().getName() + "没抢到");
            }

        }
    }

}
