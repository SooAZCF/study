package com.sooa.study.day16;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new k());
        Thread.sleep(10000);
        executorService.submit(new k());
        Thread.sleep(10000);
        executorService.submit(new k());
        Thread.sleep(10000);
        executorService.submit(new k());
        Thread.sleep(10000);
        executorService.submit(new k());
        Thread.sleep(10000);
        executorService.submit(new k());
        executorService.shutdown();
    }

    static class k extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
