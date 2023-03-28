package com.sooa.study.day09;

public class ttt {

    interface solve {
        void solvePros();
    }

    interface edit {
        void editBook();
    }

    static abstract class Employee {
        String name;
        int age;
        int salary;

        void card() {
        }

        void deCard() {
        }

        abstract void work();
    }

    static class prime_manager extends Employee implements solve, edit {

        @Override
        public void solvePros() {

        }

        @Override
        public void editBook() {

        }

        @Override
        void work() {

        }
    }

    static class teacher extends Employee implements solve, edit {

        void train() {

        }

        @Override
        public void solvePros() {

        }

        @Override
        public void editBook() {

        }

        @Override
        void work() {

        }
    }

    static class manager extends Employee implements edit {

        @Override
        public void editBook() {

        }

        @Override
        void work() {

        }
    }

    static class moniter extends Employee {
        @Override
        void work() {

        }
    }

}
