package com.sooa.study.day17;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class Receiver extends Thread {
    Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(new File("src/main/resources/" + UUID.randomUUID() + ".txt"));
            int k;
            while ((k = inputStream.read()) != -1) {
                fos.write(k);
            }
            fos.close();
//            反馈
            socket.shutdownInput();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("接受成功".getBytes());
//            关闭socket
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
