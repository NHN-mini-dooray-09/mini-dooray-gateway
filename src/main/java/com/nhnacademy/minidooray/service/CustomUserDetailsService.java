package com.nhnacademy.minidooray.service;

import com.nhnacademy.minidooray.entity.AccountDto;
import com.nhnacademy.minidooray.entity.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String url = "http://localhost:8080/accounts/login";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("id", username);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(httpHeaders, body);

        ResponseEntity<AccountDto> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                AccountDto.class
        );

        AccountDto accountDto = response.getBody();
        return new AccountGateway(accountDto.getId(), accountDto.getPassword());
    }
}
