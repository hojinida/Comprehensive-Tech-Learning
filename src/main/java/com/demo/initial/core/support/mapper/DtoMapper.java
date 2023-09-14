package com.demo.initial.core.support.mapper;

import com.demo.initial.auth.application.dto.SignInResponseDto;
import com.demo.initial.member.application.dto.MemberCreateResponseDto;
import com.demo.initial.member.domain.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface DtoMapper {
    MemberCreateResponseDto toMemberCreateResponseDto(Member member);
    SignInResponseDto toSignInResponseDto(String token);
}
