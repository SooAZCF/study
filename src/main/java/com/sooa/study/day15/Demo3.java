package com.sooa.study.day15;

public class Demo3 {
    static final Object lockObject = new Object();
    static int target = 100;

    public static void main(String[] args) {
        MyThread thread1 = new MyThread("线1：");
        MyThread thread2 = new MyThread("线2：");

        thread1.start();
        thread2.start();
    }

    static class MyThread extends Thread {
        public MyThread() {
        }

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (Demo3.class) {
                    target = checkSingle(target);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        private int checkSingle(int target) {
            if (target < 1) return -1;
            if (target % 2 == 1)
                System.out.println(Thread.currentThread().getName() + target + "为奇数");
            target--;
            return target;
        }
    }
}
