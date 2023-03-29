package com.sooa.study.day10;

import java.io.File;
import java.io.FileNotFoundException;

public class Testt {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/resources");
        k k = new k();
        if (k instanceof kk) {
            kk kk = k;
            kk.swim();
        }
    }

    static class k implements kk {

        @Override
        public void swim() {

        }
    }

    interface kk {
        void swim();
    }
}
