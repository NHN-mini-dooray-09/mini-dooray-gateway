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
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final RestTemplate restTemplate;
    private RequestAccountApi requestAccountApi;
    private final PasswordEncoder passwordEncoder;

    private final String URL = "http://localhost:8080";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String uri = "/accounts/login/{loginId}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // GET 할 때는 body가 비어도 됨
        HttpEntity<Map<String, String>> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<AccountLoginDto> response = restTemplate.exchange(
                URL + uri,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                },
                username
        );

        AccountLoginDto accountLoginDto = response.getBody();
        return new AccountGateway(accountLoginDto.getId(), accountLoginDto.getPassword());
    }

    public AccountCreateDto createAccount(AccountCreateDto accountCreateDto) {
        String uri = "/accounts/signup";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // POST 할 때는 body에 무언가 넣어서 보내야겠지? yes!
        HttpEntity<AccountCreateDto> request = new HttpEntity<>(accountCreateDto, httpHeaders);

        ResponseEntity<AccountCreateDto> response = restTemplate.exchange(
                URL + uri,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}
