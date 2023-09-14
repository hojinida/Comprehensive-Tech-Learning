package com.demo.initial.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ExceptionStatus {

    INVALID(Status.INVALID, HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(Status.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(Status.NOT_FOUND, HttpStatus.NOT_FOUND),
    UNAUTHORIZED(Status.UNAUTHORIZED, HttpStatus.UNAUTHORIZED),
    FORBIDDEN(Status.FORBIDDEN, HttpStatus.FORBIDDEN);

    private final Status status;

    @Getter
    private final HttpStatus httpStatus;

    ExceptionStatus(Status status, HttpStatus httpStatus) {
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public static ExceptionStatus from(Status input) {
        return Arrays.stream(ExceptionStatus.values())
                .filter(it -> it.status == input)
                .findAny()
                .orElse(INTERNAL_SERVER_ERROR);
    }
}
