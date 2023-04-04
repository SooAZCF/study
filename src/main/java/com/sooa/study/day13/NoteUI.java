package com.sooa.study.day13;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class NoteUI extends JFrame implements ActionListener {
    public static final Object[] COL_NAME = {"编号", "标题", "正文"};
    public static final File FILES = new File("src/main/resources/importantFile/files");
    public static final File FILES_LIST = new File("src/main/resources/importantFile/defaultList.txt");
    public static String[][] data;

    static {
        try {
            data = readFiles(FILES_LIST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //        界面内容
    JLabel jLabel = new JLabel("主列表");
    JTable jTable = new JTable(toMinBinaryArr(data, 3), COL_NAME);
    JScrollPane jScrollPane = new JScrollPane(jTable);
    JButton addButton = new JButton("添加");
    JButton updateButton = new JButton("修改");
    JButton deleteButton = new JButton("删除");
    //        初始化组件
    JMenuBar jMenuBar = new JMenuBar();
    JMenu settingMenu = new JMenu("设置");
    JMenu aboutMenu = new JMenu("关于");
    JMenuItem importMenuItem = new JMenuItem("导入");
    JMenuItem exportMenuItem = new JMenuItem("导出");
    JMenuItem exitMenuItem = new JMenuItem("退出");
    JMenuItem aboutJMenuItem = new JMenuItem("开发：SooA");
    JMenuItem msgJMenuItem = new JMenuItem("联系方式：sooa.fenton@foxmail.com");
    //    弹框
    JDialog jDialog = new JDialog();

    public NoteUI() {
        initFrame();
        initMenuBar();
        initView();
        this.setVisible(true);//是否显示界面,建议放到所有设置最后写
    }

    //    静态读取列表信息
    private static String[][] readFiles(File file) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while ((s = br.readLine()) != null) {
            strings.add(s);
        }
        String[][] strings1 = new String[strings.size()][4];
        for (int i = 0; i < strings1.length; i++) {
            s = strings.get(i);
            String[] temp = s.split("-");
            for (int i1 = 0; i1 < strings1[i].length; i1++) {
                strings1[i][i1] = temp[i1];
            }
        }
        br.close();
        return strings1;
    }

    //    数据切割为小型
    private String[][] toMinBinaryArr(String[][] data, int num) {
        String[][] strings = new String[data.length][num];
        for (int i = 0; i < data.length; i++) {
            for (int i1 = 0; i1 < data[i].length - 1; i1++) {
                strings[i][i1] = data[i][i1];
            }
        }
        return strings;
    }

    private void initView() {
//        弹框设置
        jDialog.setModal(true);
        jDialog.setSize(100, 100);
        jDialog.setLocationRelativeTo(null);
        jDialog.setAlwaysOnTop(true);
//        设置宽高属性
        jLabel.setBounds(280, 50, 40, 30);
        jTable.setBounds(50, 100, 500, 400);
        jScrollPane.setBounds(50, 100, 500, 400);

        addButton.setBounds(60, 520, 100, 50);
        updateButton.setBounds(240, 520, 100, 50);
        deleteButton.setBounds(430, 520, 100, 50);
//        添加监视
        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
//        添加到容器
        this.add(jLabel);
        this.add(jScrollPane);
        this.add(addButton);
        this.add(updateButton);
        this.add(deleteButton);
    }

    private void initMenuBar() {
//        添加功能监听
        exitMenuItem.addActionListener(this);
        importMenuItem.addActionListener(this);
        exportMenuItem.addActionListener(this);
//        加入父级容器
        settingMenu.add(importMenuItem);
        settingMenu.add(exportMenuItem);
        settingMenu.add(exitMenuItem);

        aboutMenu.add(aboutJMenuItem);
        aboutMenu.add(msgJMenuItem);

        jMenuBar.add(settingMenu);
        jMenuBar.add(aboutMenu);

        this.setJMenuBar(jMenuBar);

    }

    private void initFrame() {
        this.setSize(600, 700);
        this.setTitle("每日一记");//设置标题
        this.setLayout(null);
        this.setAlwaysOnTop(false);//压过其他软件,放置最上方
        this.setLocationRelativeTo(null);//设置居中打开
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭游戏(这里的关闭指Java虚拟机运行也关闭,不是视觉上的关闭)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (exitMenuItem.equals(source)) System.exit(0);
//        导入
        if (importMenuItem.equals(source)) {
            JFileChooser jFileChooser = new JFileChooser("");
            jFileChooser.showOpenDialog(null);
            File file = jFileChooser.getSelectedFile();//获取导入文件路路径
            if (file != null) {
                try {
                    if (safeFormatImportFiles(file)) //文件格式正确
                        data = readFiles(file);//读入数据
                    else {
                        jDialog.add(new JLabel("导入错误"));
                        jDialog.setVisible(true);
                        jDialog.removeAll();
                    }
                } catch (IOException exception) {
                    System.out.println("导入出错啦");
                }
                this.setVisible(false);//重绘界面
                new NoteUI();
            }
        }
//        导出
        if (exportMenuItem.equals(source)) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jFileChooser.showOpenDialog(null);
            File file = jFileChooser.getSelectedFile();//获取文件夹路径
            if (file != null) {
                try {
                    if (outFiles(file)) {
                        System.out.println("输出成功");
                        jDialog.add(new JLabel("导出成功"));
                        jDialog.setVisible(true);
                        jDialog.removeAll();
                    } else {
                        System.out.println("输出失败");
                        jDialog.add(new JLabel("导出失败"));
                        jDialog.setVisible(true);
                        jDialog.removeAll();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
//        添加
        if (addButton.equals(source)) {
            this.setVisible(false);
            new AddUi();
        }
//        更新
        if (updateButton.equals(source)) {
            this.setVisible(false);
            new EditUI();
        }
//        删除
        if (deleteButton.equals(source)) {
            System.out.println("删除");
        }
    }

    //    输出
    private boolean outFiles(File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file, "backup.txt")));
        for (int i = 0; i < data.length; i++) {
            String s;
            s = data[i][0] + "-" + data[i][1] + "-" + data[i][2] + "-" + data[i][3];
            bw.write(s);
            bw.newLine();
        }
        bw.close();
        return true;
    }

    //    验证文件内容格式是否符合规范
    private boolean safeFormatImportFiles(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = br.readLine();
        if (s == null) return false;//判空
        String regex = "(\\d)+-(.)+-(.){1,50}-([a-zA-Z]:(\\\\w+)*\\\\w+.txt)";//123-撒娇的和反抗精神-啥叫哈-D:\\a.txt
        while ((s = br.readLine()) != null) {
//            正则表达式验证数据格式
            System.out.println(s);
            if (!s.matches(regex)) return false;
        }
        return true;
    }
}
