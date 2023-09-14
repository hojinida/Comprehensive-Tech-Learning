package com.demo.initial.auth.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class NaverUserInfo implements UserInfo{

    private final Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        log.info(attributes.toString());
        this.attributes = attributes;
    }
    @Override
    public String getEmail() {
        Map<String, Object> response = asMap(attributes.get("response"));
        return (String)response.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> response = asMap(attributes.get("response"));
        return (String)response.get("name");
    }

    @Override
    public String getPhoneNumber() {
        Map<String, Object> response = asMap(attributes.get("response"));
        return (String)response.get("mobile");
    }

    @Override
    public Map<String, Object> toAttributes() {
        return this.attributes;
    }

    private static Map<String, Object> asMap(Object obj) {
        return Optional.ofNullable(obj)
                .filter(o -> o instanceof Map<?, ?>)
                .map(o -> (Map<String, Object>) o)
                .orElseThrow(() -> new IllegalArgumentException("Not a map: " + obj));
    }
}
