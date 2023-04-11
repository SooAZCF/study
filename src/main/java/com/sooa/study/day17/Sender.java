package com.sooa.study.day17;

import java.io.*;
import java.net.Socket;

public class Sender extends Thread {
    public Sender() {
    }

    public Sender(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
//        建立连接
            Socket socket = new Socket("localhost", 30000);
            System.out.println(socket);
//        获取流
            FileInputStream fis = new FileInputStream(new File("src/main/resources/info.txt"));
            OutputStream outputStream = socket.getOutputStream();
            int k;
            while ((k = fis.read()) != -1) {
//        发送
                outputStream.write(k);
            }
            fis.close();
//        获取回馈
            socket.shutdownOutput();
            Reader reader = new InputStreamReader(socket.getInputStream());
            int kk;
            while ((kk = reader.read()) != -1) {
                System.out.print((char) kk);
            }
            System.out.println();
//            关闭socket
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
