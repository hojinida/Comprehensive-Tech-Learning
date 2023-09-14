package com.demo.initial.member.domain;


import com.demo.initial.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "email", columnList = "email", unique = true))
public class Member extends BaseEntity {
    @Embedded
    private Email email;
    @Embedded
    private Name name;
    @Embedded
    private PhoneNumber phoneNumber;

    @Getter
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Builder
    public Member(Long id, String email, String name,String phoneNumber,MemberType memberType) {
        super(id);
        this.email=new Email(email);
        this.name = new Name(name);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.memberType=memberType;
    }

    public String getEmail() {
        return this.email.getValue();
    }

    public String getName() {
        return this.name.getValue();
    }

    public String getPhoneNumber() {
        return this.phoneNumber.getValue();
    }

    public void changeName(String name){
        this.name = new Name(name);
    }
    public void changePhoneNumber(String phoneNumber){
        this.phoneNumber=new PhoneNumber(phoneNumber);
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
        Member member = (Member) o;
        return  Objects.equals(name, member.name) && Objects.equals(email, member.email)
                && Objects.equals(phoneNumber, member.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name,phoneNumber);
    }

    public boolean isSameId(Long memberId) {
        return this.id.equals(memberId);
    }

}
