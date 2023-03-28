package com.sooa.study.day09;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        File input = new File("src/main/resources/aaa");
        File output = new File("src/main/resources/sss");
        if (copyFolders(input, output)) System.out.println("成功");
        else System.out.println("失败");
    }

    private static boolean copyFolders(File input, File output) throws IOException {
//        验证数据安全
        if (!input.exists() || input.isFile()) return false;
        if (!output.exists()) output.mkdirs();
//        复制
//        复制完这一层文件
        copyFile(input, output);
//        进入下一层文件夹
        File[] list = input.listFiles();
        for (File file : list) {
            if (file.isDirectory()) copyFolders(file, new File(output, file.getName()));
        }
        return true;
    }

    private static void copyFile(File input, File output) throws IOException {
        File[] list = input.listFiles();
        for (File file : list) {
            if (file.isFile()) {
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = new FileOutputStream(new File(output, file.getName()));
                byte[] bytes = new byte[1024 * 5 * 1024];
                int length;
                while ((length = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                }
                inputStream.close();
                outputStream.close();
            }
        }
    }

}
