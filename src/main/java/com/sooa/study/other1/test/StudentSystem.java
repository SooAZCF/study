package com.sooa.study.other1.test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    final static int SHOW_STUDENT = 1;
    final static int ADD_STUDENT = 2;
    final static int DELETE_STUDENT = 3;
    final static int UPDATE_STUDENT = 4;
    final static int QUERY_STUDENT = 5;
    final static int EXIT = 6;

    final static String NAME_REGEX = "[\\u4e00-\\u9fa5]{2,4}";
    final static String GENDER_REGEX = "男|女";
    final static String NUM_REGEX = "\\d{1,3}";
    final static String PHONE_REGEX = "1\\d{10}";
    final static String EMAIL_REGEX = "\\w+@\\w+\\.com";

    static ArrayList<Student> students = new ArrayList<>();

    //    初始化
    static {
        students.add(new Student("test", "女", 66, "13401711986", "sooazcf@gmail.com"));
    }

    //    主系统
    public StudentSystem() {
        while (true) {
            System.out.println();
            System.out.print("欢迎进入学生管理系统\n" +
                    "1.显示学生\n" +
                    "2.增加学生\n" +
                    "3.删除学生\n" +
                    "4.修改学生\n" +
                    "5.查询学生\n" +
                    "6.退出系统\n" +
                    "请输入选择：");
            switch (new Scanner(System.in).nextInt()) {
                case SHOW_STUDENT -> showFunction();
                case ADD_STUDENT -> addFunction();
                case DELETE_STUDENT -> deleteFunction();
                case UPDATE_STUDENT -> updateFunction();
                case QUERY_STUDENT -> queryFunction();
                case EXIT -> System.exit(0);
                default -> System.out.println("输入有误，请重新输入。");
            }
        }
    }

    //    显示模块
    private void showFunction() {
        showAllStudent();
    }

    //    添加模块
    private void addFunction() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Student student = null;
        while (true) {
            System.out.print("请依次输入学生信息(姓名，性别，年龄，手机号，邮箱，间隔为空格,输入0为返回)：");
            String test = scanner.next();
            if ("0".equals(test)) return;
            student = new Student(test, scanner.next(), scanner.nextInt(), scanner.next(), scanner.next());
            if (!checkSafeInput(student)) {
                System.out.println("输入有误，请重新输入");
                continue;
            }
            break;
        }
        addStudent(student);
        System.out.println("添加成功");
    }

    //    删除模块
    private void deleteFunction() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        while (true) {
            System.out.print("请依次输入学生信息(姓名，性别，年龄，手机号，邮箱，间隔为空格,输入0为返回)：");
            String test = scanner.next();
            if ("0".equals(test)) return;
            Student student = new Student(test, scanner.next(), scanner.nextInt(), scanner.next(), scanner.next());
            if (checkSafeInput(student)) {
                if (deleteStudent(student)) {
                    System.out.println("删除成功");
                    break;
                } else System.out.println("删除失败，查无此人");

            } else System.out.println("输入有误，请重新输入");
        }

    }

    //    修改模块
    private void updateFunction() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Student student = null;
        Student student1 = null;
        while (true) {
            System.out.print("请依次输入该学生信息(姓名，性别，年龄，手机号，邮箱，间隔为空格,输入0为返回)：");
            String test = scanner.next();
            if ("0".equals(test)) return;
            student = new Student(test, scanner.next(), scanner.nextInt(), scanner.next(), scanner.next());

            if (!checkSafeInput(student)) {
                System.out.println("输入有误，请重新输入");
                continue;
            }
            if (!checkStudent(student)) {
                System.out.println("查无此人");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("请依次输入修改后学生信息(姓名，性别，年龄，手机号，邮箱，间隔为空格,输入0为返回)：");
            String test = scanner.next();
            if ("0".equals(test)) return;
            student1 = new Student(test, scanner.next(), scanner.nextInt(), scanner.next(), scanner.next());

            if (!checkSafeInput(student1)) {
                System.out.println("输入有误，请重新输入");
                continue;
            }
            break;
        }
        updateStudent(student, student1);
        System.out.println("修改成功");
    }

    //    查询模块
    private void queryFunction() {
        String[] targets;
        while (true) {
            System.out.print("请输入查询项(多个词条请用横杠连接)：");
            String target = new Scanner(System.in, StandardCharsets.UTF_8).nextLine();
            targets = target.split("-");
            if (!checkSafeInput(target)) {
                System.out.println("输入有误，请重新输入");
                continue;
            }
            for (int i = 0; i < targets.length; i++) {
                if (!checkSafeInput(targets[i])) {
                    System.out.println("输入有误，请重新输入");
                }
            }
            break;
        }
        String[] strings = sortArrToStudent(targets);
        queryStudent(new Student(strings[0], strings[1], strings[2] == null ? 0 : Integer.parseInt(strings[2]), strings[3], strings[4]));
    }

    private void addStudent(Student student) {
        students.add(student);
    }

    private void showAllStudent() {
        System.out.println("------------------------------------------");
        System.out.println("姓名\t\t性别\t年龄\t手机号\t\t邮箱");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("------------------------------------------");
    }

    private boolean deleteStudent(Student student) {
        if (checkStudent(student)) {
            students.remove(student);
            return true;
        } else return false;
    }

    private void updateStudent(Student student, Student student1) {
        students.set(students.indexOf(student), student1);
    }

    private void queryStudent(Student student) {
        System.out.println("------------------------------------------");
        for (Student student1 : students) {
            if (student1.getName().equals(student.getName()) || student.getName() == null)
                if (student1.getGender().equals(student.getGender()) || student.getGender() == null)
                    if (student1.getAge() == student.getAge() || student.getAge() == 0)
                        if (student1.getPhone().equals(student.getPhone()) || student.getPhone() == null)
                            if (student1.getEmail().equals(student.getEmail()) || student.getEmail() == null)
                                System.out.println(student1);
        }
        System.out.println("------------------------------------------");
    }

    //    工具类
    private String[] sortArrToStudent(String[] targets) {
        String[] strings = new String[5];
        for (String target : targets) {
            if (target.matches(NAME_REGEX)) strings[0] = target;
            if (target.matches(GENDER_REGEX)) strings[1] = target;
            if (target.matches(NUM_REGEX)) strings[2] = target;
            if (target.matches(PHONE_REGEX)) strings[3] = target;
            if (target.matches(EMAIL_REGEX)) strings[4] = target;
        }
        return strings;
    }

    private boolean checkStudent(Student student) {
        if (students.contains(student)) return true;
        else return false;
    }

    private boolean checkSafeInput(Student student) {
        if (student == null) return false;
        String name = student.getName();
        String gender = student.getGender();
        int age = student.getAge();
        String phone = student.getPhone();
        String email = student.getEmail();

        if (name == null || "".equals(name)) return false;
        if (!name.matches(NAME_REGEX)) return false;

        if (gender == null || "".equals(gender)) return false;
        if (!gender.matches(GENDER_REGEX)) return false;

        if (age <= 0 && age > 150) return false;

        if (phone == null || "".equals(phone)) return false;
        if (!phone.matches(PHONE_REGEX)) return false;

        if (email == null || "".equals(email)) return false;
        if (!email.matches(EMAIL_REGEX)) return false;


        return true;
    }

    private boolean checkSafeInput(String target) {
        if (target == null) return false;
        if ("".equals(target.trim())) return false;
        return true;
    }


}
