package com.sooa.study.day17;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ReceiverApp {
    public static void main(String[] args) throws IOException {
//        线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 6, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //            建立服务器
        ServerSocket serverSocket = new ServerSocket(30000);
        while (true) {
            Socket socket = serverSocket.accept();
            threadPoolExecutor.submit(new Receiver(socket));
        }

    }
}
