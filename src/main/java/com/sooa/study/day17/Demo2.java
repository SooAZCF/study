package com.sooa.study.day17;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = new ServerSocket(10086);

        Socket socket = socketServer.accept();
        BufferedReader bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        int k;
        while ((k = bis.read()) != -1) {
            System.out.print((char) k);
        }

        socket.close();
        socketServer.close();
    }
}
