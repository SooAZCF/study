package com.sooa.study.day13;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class test {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
//        properties.load(new FileReader(new File("src/main/resources/sss.properties")));
        properties.put("ttt", "sss");
        properties.put("lll", "uyyy");
        properties.setProperty("sss", "ggg");
        properties.store(new FileWriter("src/main/resources/sss.properties"), "test");


    }
}
