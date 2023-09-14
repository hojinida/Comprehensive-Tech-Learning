package com.demo.initial.member.exception;

import com.demo.initial.core.exception.ExceptionType;
import com.demo.initial.core.exception.Status;

public enum MemberExceptionType implements ExceptionType {

    NOT_FOUND(Status.NOT_FOUND, 3001, "회원이 없습니다"),
    NOT_FOUND_ROLE(Status.NOT_FOUND, 3002, "일치하는 권한이 없습니다"),
    INVALID_VALUE(Status.INVALID,3003,"유효하지 않은 값입니다.");

    private final Status status;
    private final int exceptionCode;
    private final String message;

    MemberExceptionType(Status status, int exceptionCode, String message) {
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
