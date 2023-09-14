package com.demo.initial.auth.application.oauth2;

import com.demo.initial.auth.application.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException{
        // 현재 인증된 사용자의 정보를 가져옵니다.
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> kakaoAccount = oauth2User.getAttribute("kakao_account");
        String userEmail = (String) kakaoAccount.get("email");

        // 회원 정보를 사용하여 토큰 생성
        String token = tokenProvider.createToken(userEmail);

        // JSON 응답 생성
        String jsonResponse = "{\"accessToken\":\"" + token + "\"}";

        // 응답 설정 및 출력
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

}
