package com.demo.initial.member.domain;

import com.demo.initial.member.exception.MemberException;
import com.demo.initial.member.exception.MemberExceptionType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
public class PhoneNumber {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 255;

    @Column(name = "phone_number", nullable = false)
    private String value;

    public PhoneNumber(String value) {
        validateBlank(value);
        validateLength(value);
        this.value = value;
    }

    private void validateBlank(String value) {
        if (value.isBlank()) {
            throw new MemberException(MemberExceptionType.INVALID_VALUE);
        }
    }

    private void validateLength(String value) {
        int length = value.length();
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new MemberException(MemberExceptionType.INVALID_VALUE);
        }
    }

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneNumber oAuthId = (PhoneNumber) o;
        return Objects.equals(value, oAuthId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
