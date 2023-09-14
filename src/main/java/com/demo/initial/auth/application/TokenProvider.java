package com.demo.initial.auth.application;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface TokenProvider {
    Authentication getAuthentication(String token);
    String resolveToken(HttpServletRequest request);
    String createToken(String payload);

    String parsePayload(String token);

    Boolean validateToken(String token);
}
