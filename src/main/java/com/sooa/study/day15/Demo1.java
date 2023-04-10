package com.sooa.study.day15;

public class Demo1 {
    static final Object lockObject = new Object();
    static int ticketNum = 1000;

    public static void main(String[] args) {
        Window window1 = new Window("窗口1");
        Window window2 = new Window("窗口2");

        window1.start();
        window2.start();
    }

    static class Window extends Thread {
        public Window() {
        }

        public Window(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lockObject) {
                    if (ticketNum < 1) break;
                    ticketNum--;
                    System.out.println(Thread.currentThread().getName() + "：已出售一张电影票，还剩" + ticketNum + "张");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
