package com.demo.initial.member.application;

import com.demo.initial.member.domain.MemberRepository;
import com.demo.initial.core.support.mapper.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final DtoMapper dtoMapper;

}
