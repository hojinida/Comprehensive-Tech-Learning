package com.demo.initial.auth.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthAccessTokenResponseDto {
    @JsonProperty("access_token")
    private String accessToken;
}
