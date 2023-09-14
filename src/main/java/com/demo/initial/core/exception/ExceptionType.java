package com.demo.initial.core.exception;

public interface ExceptionType {
    Status status();

    int exceptionCode();

    String message();
}
