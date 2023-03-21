package com.sooa.study.day05;

import java.io.*;

public class Copy {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("src/main/resources/aaa");
        File outputFile = new File("src/main/resources/day05");
//        拷贝一个文件夹，考虑子文件夹
        if (copyFolder(inputFile, outputFile)) {
            System.out.println("复制成功！");
        } else {
            System.out.println("复制失败！");
        }

    }

    private static boolean copyFolder(File inputFile, File outputFile) throws IOException {
//        限定文件夹
        if (inputFile.exists()) {
            if (!inputFile.isDirectory()) {
                System.out.println("输入目录非文件夹！");
                return false;
            }
        } else {
            System.out.println("输入目录不存在！");
            return false;
        }
        if (outputFile.exists()) {
            if (!outputFile.isDirectory()) {
                System.out.println("输出目录非文件夹！");
                return false;
            }
        } else {
            System.out.println("输出目录不存在！");
            return false;
        }
//        复制当前目录文件
        copyFile(inputFile, outputFile);
//        判断剩余文件夹
        for (File s : inputFile.listFiles()) {
            if (s.isDirectory()) {
//                拼接输出文件夹
                File temp = new File(outputFile, s.getName());
                temp.mkdir();
//                迭代
                copyFolder(s, temp);
            }
        }
        return true;
    }

    private static void copyFile(File inputFile, File outputFile) throws IOException {
//        遍历当前目录文件路径
        for (File s : inputFile.listFiles()) {
//            判断为文件则复制
            if (s.isFile()) {
                InputStream fileInputStream = new FileInputStream(s);
                OutputStream fileOutputStream = new FileOutputStream(new File(outputFile, s.getName()));

                int length;
                byte[] bytes = new byte[1024];
                while ((length = fileInputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, length);
                }

                fileOutputStream.flush();
                fileOutputStream.close();
                fileInputStream.close();
            }
        }
    }
}
