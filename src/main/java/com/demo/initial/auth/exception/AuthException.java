package com.demo.initial.auth.exception;

import com.demo.initial.core.exception.BaseException;
import com.demo.initial.core.exception.ExceptionType;

public class AuthException extends BaseException {
    public AuthException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
