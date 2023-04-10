package com.sooa.study.day14;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Test {


    public static void main(String[] args) {
        Window w1 = new Window("窗口1");
        Window w2 = new Window("窗口2");
        Window w3 = new Window("窗口3");

        w1.start();
        w2.start();
        w3.start();


    }

    static class Window extends Thread implements Lock {
        static int count = 0;

        public Window() {
        }

        public Window(String name) {
            super(name);
        }

        private static synchronized void tongbuMethod() {
            count++;
            System.out.println(Thread.currentThread().getName() + ":正在出售第" + count + "张票");
        }

        @Override
        public void run() {
            while (count < 100) {
                tongbuMethod();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public void lock() {

        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {

        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }
}
