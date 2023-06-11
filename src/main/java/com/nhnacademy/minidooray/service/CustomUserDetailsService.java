package com.nhnacademy.minidooray.service;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import com.nhnacademy.minidooray.account.AccountGateway;
import com.nhnacademy.minidooray.account.AccountLoginDto;
import com.nhnacademy.minidooray.util.RequestAccountApi;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final RestTemplate restTemplate;
    private RequestAccountApi requestAccountApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String url = "http://localhost:8080/getAccounts/{loginId}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

//        Map<String, String> body = new HashMap<>();
//        body.put("id", username);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<AccountLoginDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                },
                username
        );

        AccountLoginDto accountLoginDto = response.getBody();
        return new AccountGateway(accountLoginDto.getId(), accountLoginDto.getPassword());
    }
}
