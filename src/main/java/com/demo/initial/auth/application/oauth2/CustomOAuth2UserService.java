package com.demo.initial.auth.application.oauth2;

import com.demo.initial.auth.domain.KakaoUserInfo;
import com.demo.initial.auth.domain.NaverUserInfo;
import com.demo.initial.auth.domain.UserInfo;
import com.demo.initial.auth.domain.UserPrincipal;
import com.demo.initial.member.domain.Email;
import com.demo.initial.member.domain.Member;
import com.demo.initial.member.domain.MemberRepository;
import com.demo.initial.member.domain.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        UserInfo userInfo = getUserInfo(registrationId, oAuth2User.getAttributes());

        Member member= saveOrUpdate(userInfo);

        return UserPrincipal.create(member, oAuth2User.getAttributes());
    }

    private UserInfo getUserInfo(String registrationId, Map<String, Object> attributes) {
        switch (registrationId) {
            case "naver":
                return new NaverUserInfo(attributes);
            case "kakao":
                return new KakaoUserInfo(attributes);
            default:
                throw new IllegalArgumentException("Unsupported registration id: " + registrationId);
        }
    }

    private Member saveOrUpdate(UserInfo userInfo) {
        Email emailObject = new Email(userInfo.getEmail());

        return memberRepository.findByEmail(emailObject)
                .map(entity -> {
                    if (!entity.getName().equals(userInfo.getName())) {
                        entity.changeName(userInfo.getName());
                    }
                    if (!entity.getPhoneNumber().equals(userInfo.getPhoneNumber())) {
                        entity.changePhoneNumber(userInfo.getPhoneNumber());
                    }
                    return entity;
                })
                .orElseGet(() -> {
                    // Save to DB if new user
                    return memberRepository.save(Member.builder().email(userInfo.getEmail())
                            .name(userInfo.getName()).phoneNumber(userInfo.getPhoneNumber()).memberType(MemberType.TEMPORARY_USER).build());
                });
    }
}

