package com.demo.initial.auth.domain;

import com.demo.initial.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Getter
public class UserPrincipal implements OAuth2User, UserDetails {

    private transient final Member member;

    private transient Map<String, Object> attributes;
    private UserPrincipal(Member member) {
        this.member = member;
    }
    public static UserPrincipal create(Member member) {
        return new UserPrincipal(member);
    }
    public static UserPrincipal create(Member member, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(member);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(getRoleFromMemberType()));
    }

    private String getRoleFromMemberType() {
        return switch (this.member.getMemberType()) {
            case TEMPORARY_USER -> "ROLE_TEMPORARY_USER";
            case BASIC_USER -> "ROLE_BASIC_USER";
            case AGENCY_USER -> "ROLE_AGENCY_USER";
            case ADMINISTRATOR -> "ROLE_ADMINISTRATOR";
            default -> throw new IllegalArgumentException("Unsupported member type: " + this.member.getMemberType());
        };
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
