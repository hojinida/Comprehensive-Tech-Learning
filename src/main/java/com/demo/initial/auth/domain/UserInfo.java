package com.demo.initial.auth.domain;

import java.util.Map;

public interface UserInfo {
    String getEmail();
    String getName();
    String getPhoneNumber();
    Map<String, Object> toAttributes();
}
