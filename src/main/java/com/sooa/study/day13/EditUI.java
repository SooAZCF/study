package com.sooa.study.day13;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUI extends JFrame implements ActionListener {
    //        添加组件
    JLabel jLabel = new JLabel("修改记录");
    JLabel titleLabel = new JLabel("标题");
    JTextField titleTextField = new JTextField();
    JLabel contentLabel = new JLabel("内容");
    JTextArea contentTextArea = new JTextArea();
    JScrollPane jScrollPane = new JScrollPane(contentTextArea);
    JButton updateButton = new JButton("修改");
    JButton resetButton = new JButton("重置");

    public EditUI() {
        initFrame();
//        initMenu();
        initView();
        this.setVisible(true);
    }

    private void initView() {
//        绑定事件
        updateButton.addActionListener(this);
        resetButton.addActionListener(this);
//        设置属性
        jLabel.setBounds(270, 20, 50, 30);
        titleLabel.setBounds(50, 70, 30, 30);
        titleTextField.setBounds(80, 70, 470, 30);
        contentLabel.setBounds(50, 120, 30, 30);
        jScrollPane.setBounds(80, 120, 470, 150);
        contentTextArea.setBounds(80, 120, 470, 150);

        updateButton.setBounds(150, 300, 100, 30);
        resetButton.setBounds(350, 300, 100, 30);

//        添加到容器
        this.add(jLabel);
        this.add(titleLabel);
        this.add(titleTextField);
        this.add(contentLabel);
        this.add(jScrollPane);
        this.add(updateButton);
        this.add(resetButton);

    }

    private void initMenu() {

    }

    private void initFrame() {
        this.setLayout(null);
        this.setSize(600, 400);
        this.setTitle("每日一记");//设置标题
        this.setAlwaysOnTop(false);//压过其他软件,放置最上方
        this.setLocationRelativeTo(null);//设置居中打开
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭游戏(这里的关闭指Java虚拟机运行也关闭,不是视觉上的关闭)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        if (updateButton.equals(target)) {
            System.out.println("修改");
            this.setVisible(false);
            new NoteUI();
        }
        if (resetButton.equals(target)) {
            System.out.println("重置");
        }
    }
}
