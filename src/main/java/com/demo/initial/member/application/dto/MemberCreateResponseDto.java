package com.demo.initial.member.application.dto;


import lombok.Data;

@Data
public class MemberCreateResponseDto {
    private String email;
    private String password;
}
