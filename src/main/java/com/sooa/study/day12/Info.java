package com.sooa.study.day12;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Info {
    private static final String characterSite = "https://hanyu.baidu.com/shici/detail?from=kg1&highlight=&pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&srcid=51369";
    private static final String boyNameSite = "http://www.haoming8.cn/baobao/10881.html";
    private static final String girlNameSite = "http://www.haoming8.cn/baobao/7641.html";

    private static ArrayList<String> characterArr = new ArrayList<>();
    private static ArrayList<String> boyArr = new ArrayList<>();
    private static ArrayList<String> girlArr = new ArrayList<>();

    static {
        try {
            ArrayList<String> characterTempArr = getData(webCrawler(characterSite), "([\\u4E00-\\u9FA5]{4})(，|。)", 1);
            ArrayList<String> boyTempArr = getData(webCrawler(boyNameSite), "([\\u4E00-\\u9FA5]{2})(、|。)", 1);
            ArrayList<String> girlTempArr = getData(webCrawler(girlNameSite), "([\\u4E00-\\u9FA5]{2}\\s){4}(..)", 0);
//        姓名
            for (int i = 0; i < characterTempArr.size(); i++) {
                String temp = characterTempArr.get(i);
                for (int i1 = 0; i1 < temp.length(); i1++) {
                    characterArr.add(temp.charAt(i1) + "");
                }
            }
//        男孩
            boyArr = boyTempArr;
//        女孩
            for (int i = 0; i < girlTempArr.size(); i++) {
                String temp = girlTempArr.get(i);
                String[] k = temp.split("\\s");
                for (int i1 = 0; i1 < k.length; i1++) {
                    girlArr.add(k[i1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateInfo(int boyNum, int girlNum, String path) throws IOException {
        File file = new File(path);
        if (path.endsWith(".txt")) {
            writeToFile(boyNum, girlNum, file);
        } else {
            writeToFile(boyNum, girlNum, new File(file, file.getName() + ".txt"));
        }
    }

    private static void writeToFile(int boyNum, int girlNum, File file) throws IOException {
        ArrayList<String> arrayList = generateInfo(boyNum, girlNum);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        arrayList.forEach(o -> {
            try {
                bw.write(o);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fileWriter.close();
    }

    public static ArrayList<String> generateInfo(int boyNum, int girlNum) {
        Random rd = new Random();
        ArrayList<String> infoList = new ArrayList<>();
//        男生生成
        HashSet<String> nameList = new HashSet<>();
        while (true) {
            int a1 = rd.nextInt(characterArr.size());
            int a2 = rd.nextInt(boyArr.size());
            nameList.add(characterArr.get(a1) + boyArr.get(a2));
            if (boyNum == nameList.size()) break;
        }
//        年龄-性别
        for (String s : nameList) {
            int k = rd.nextInt(8) + 18;
            String ss = s + "-男-" + k;
            infoList.add(ss);
        }
//        女生生成

        nameList.clear();
        while (true) {
            int a1 = rd.nextInt(characterArr.size());
            int a3 = rd.nextInt(girlArr.size());
            nameList.add(characterArr.get(a1) + boyArr.get(a3));
            if (girlNum == nameList.size()) break;
        }
//        年龄-性别
        for (String s : nameList) {
            int k = rd.nextInt(8) + 18;
            String ss = s + "-女-" + k;
            infoList.add(ss);
        }
        Collections.shuffle(infoList);
        return infoList;
    }

    private static ArrayList<String> getData(String webCrawler, String regex, int index) {
        ArrayList<String> list = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(webCrawler);
        while (m.find()) {
            list.add(m.group(index));
        }
        return list;
    }

    private static String webCrawler(String site) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(site);
        URLConnection conn = url.openConnection();
        InputStreamReader isr = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
        int chs;
        while ((chs = isr.read()) != -1) {
            sb.append((char) chs);
        }
        return sb.toString();
    }
}
