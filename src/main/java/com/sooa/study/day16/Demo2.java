package com.sooa.study.day16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo2 {
    public static void main(String[] args) {
//        自定义线程池
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                3,//核心线程数
                6,//总线程数，减去核心数为临时数
                60,//存活时间
                TimeUnit.SECONDS,//指定工具类中单位
                new ArrayBlockingQueue<>(4),//阻塞队列，无限制则为new LinkedBlockingQueue<>
                Executors.defaultThreadFactory(),//线程工厂,用执行器工具类中默认的工厂
                new ThreadPoolExecutor.AbortPolicy()//线程拒绝策略，属于线程池执行器内部类
        );
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());
        tpe.submit(new Demo1.k());

        tpe.shutdown();

    }
}
