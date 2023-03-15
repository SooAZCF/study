package com.sooa.study.day01;

public class CustomException extends Exception {
    public CustomException() {
    }

    public CustomException(String message) {
        System.out.println("异常为" + message);
    }
}
