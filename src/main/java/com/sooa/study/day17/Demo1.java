package com.sooa.study.day17;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Demo1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10086);
        OutputStream os = socket.getOutputStream();
        os.write("666".getBytes());
        os.close();
        socket.close();
    }
}
