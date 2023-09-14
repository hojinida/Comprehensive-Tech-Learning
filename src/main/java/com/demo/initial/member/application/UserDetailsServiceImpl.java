package com.demo.initial.member.application;

import com.demo.initial.auth.domain.UserPrincipal;
import com.demo.initial.member.domain.Email;
import com.demo.initial.member.domain.Member;
import com.demo.initial.member.domain.MemberRepository;
import com.demo.initial.member.exception.MemberException;
import com.demo.initial.member.exception.MemberExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String principal) throws UsernameNotFoundException {
        log.info(principal);
        Email email=new Email(principal);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND));

        return UserPrincipal.create(member);
    }
}
