package com.demo.initial.member.exception;

import com.demo.initial.core.exception.BaseException;
import com.demo.initial.core.exception.ExceptionType;

public class MemberException extends BaseException {
    public MemberException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}