package com.demo.initial.auth.exception;

import com.demo.initial.core.exception.ExceptionType;
import com.demo.initial.core.exception.Status;

public enum AuthExceptionType implements ExceptionType {
    INVALID_AUTHORIZATION(Status.UNAUTHORIZED, 2001, "유효하지 않은 인증입니다."),
    EXPIRED_AUTHORIZATION(Status.UNAUTHORIZED,2002,"만료된 인증입니다.")
    ;

    private final Status status;
    private final int exceptionCode;
    private final String message;

    AuthExceptionType(Status status, int exceptionCode, String message) {
        this.status = status;
        this.exceptionCode = exceptionCode;
        this.message = message;
    }

    @Override
    public Status status() {
        return status;
    }

    @Override
    public int exceptionCode() {
        return exceptionCode;
    }

    @Override
    public String message() {
        return message;
    }
}
