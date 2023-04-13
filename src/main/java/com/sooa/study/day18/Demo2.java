package com.sooa.study.day18;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Properties;

public class Demo2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        加载prop文件
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/main/resources/prop.properties")));
//        获取prop信息
        String classname = (String) properties.get("classname");
        Class clazz = Class.forName(classname);
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class, char.class, double.class, String.class);//不写参数class就为空参构造
        Student o = (Student) constructor.newInstance("你爹", 66, '男', 166, "找你爹");
        System.out.println(o);
    }

    static class Student {
        String name;
        int age;
        char gender;
        double height;
        String hobby;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Demo1.Student student = (Demo1.Student) o;
            return age == student.age && gender == student.gender && Double.compare(student.height, height) == 0 && Objects.equals(name, student.name) && Objects.equals(hobby, student.hobby);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, gender, height, hobby);
        }

        public Student() {
        }

        public Student(String name, int age, char gender, double height, String hobby) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.height = height;
            this.hobby = hobby;
        }

        /**
         * 获取
         *
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置
         *
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取
         *
         * @return age
         */
        public int getAge() {
            return age;
        }

        /**
         * 设置
         *
         * @param age
         */
        public void setAge(int age) {
            this.age = age;
        }

        /**
         * 获取
         *
         * @return gender
         */
        public char getGender() {
            return gender;
        }

        /**
         * 设置
         *
         * @param gender
         */
        public void setGender(char gender) {
            this.gender = gender;
        }

        /**
         * 获取
         *
         * @return height
         */
        public double getHeight() {
            return height;
        }

        /**
         * 设置
         *
         * @param height
         */
        public void setHeight(double height) {
            this.height = height;
        }

        /**
         * 获取
         *
         * @return hobby
         */
        public String getHobby() {
            return hobby;
        }

        /**
         * 设置
         *
         * @param hobby
         */
        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public String toString() {
            return "Student{name = " + name + ", age = " + age + ", gender = " + gender + ", height = " + height + ", hobby = " + hobby + "}";
        }
    }
}
