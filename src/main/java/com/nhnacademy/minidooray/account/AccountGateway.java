package com.nhnacademy.minidooray.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Getter
@Setter
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountGateway implements UserDetails, OAuth2User {
    private String username;
    private String password;
//    private Long accountId;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<GrantedAuthority> authorities;
    private transient Map<String, Object> attributes;

    public AccountGateway(String username, String password) {
        this.username = username;
        this.password = password;
//        this.accountId = accountId;
        attributes = new HashMap<>();
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
        enabled = true;
        authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"));
    }

    @Override
    public String getName() {
        return getUsername();
    }
}
