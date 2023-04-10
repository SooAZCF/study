package com.sooa.study.day14;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> s = new FutureTask<>(new Main());

        s.run();
        System.out.println(s.get());
    }

    @Override
    public String call() {
        return String.valueOf(Thread.currentThread().getPriority());
    }
}
