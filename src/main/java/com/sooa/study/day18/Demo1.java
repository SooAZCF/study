package com.sooa.study.day18;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.UUID;

public class Demo1 {
    public static void main(String[] args) {
        Student student = new Student("你爹", 66, '男', 193, "进入小学");
        try {
            saveObject(student);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveObject(Student student) throws IllegalAccessException, IOException {
        Field[] fields = student.getClass().getDeclaredFields();
        FileOutputStream fos = null;
        String uid = UUID.randomUUID().toString();
        for (Field field : fields) {
            field.setAccessible(true);
            String value = field.getName() + "=" + field.get(student);
            try {
                fos = new FileOutputStream(new File("src/main/resources/" + uid + ".txt"), true);
                fos.write((value + "\r\n").getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        fos.close();
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
            Student student = (Student) o;
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
