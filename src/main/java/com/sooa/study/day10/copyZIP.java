package com.sooa.study.day10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class copyZIP {
    public static void main(String[] args) throws IOException {
        File in = new File("src/main/resources/aaa");//压缩的对象
        File out = in.getParentFile();//压缩对象的存放处
        String entryPath = in.getName();
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(out, in.getName() + ".zip")));//关联要输出的路径对象
        toZip(in, entryPath, zos);
        zos.closeEntry();
        zos.close();
    }

    private static void toZip(File in, String entryPath, ZipOutputStream zos) throws IOException {
        File[] files = in.listFiles();
        for (File file : files) {//遍历in的目录
            if (file.isFile()) {
//                核心
                ZipEntry entry = new ZipEntry(new File(entryPath, file.getName()).toString());//填入在压缩文件中的路径，注意路径是带后缀的文件不是文件夹

                zos.putNextEntry(entry);//在zip中生成
                FileInputStream fis = new FileInputStream(file);//读取要复制的文件数据
                int length;
                byte[] bytes = new byte[1024 * 1024 * 5];//缓存数组
                while ((length = fis.read(bytes)) != -1) {//复制
                    zos.write(bytes, 0, length);//用zip文件流来进行写入到压缩文件内
                }
                fis.close();//关流

            } else {
                entryPath = new File(entryPath, file.getName()).toString();//下一层的目录
                toZip(file, entryPath, zos);
            }

        }

    }
}
