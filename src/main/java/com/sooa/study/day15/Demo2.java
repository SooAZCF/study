package com.sooa.study.day15;

public class Demo2 {
    static final Object lockObject = new Object();
    static int presentNum = 100;

    public static void main(String[] args) {
        Person person1 = new Person("人1");
        Person person2 = new Person("人2");

        person1.start();
        person2.start();
    }

    static class Person extends Thread {
        public Person() {
        }

        public Person(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lockObject) {
                    if (presentNum < 10) break;
                    presentNum--;
                    System.out.println(Thread.currentThread().getName() + "：已送出一份礼物，还剩" + presentNum + "份");
                }
            }
        }
    }

}
