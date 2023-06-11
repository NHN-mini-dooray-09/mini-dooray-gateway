package com.nhnacademy.minidooray.controller;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import com.nhnacademy.minidooray.account.AccountLoginDto;
import com.nhnacademy.minidooray.service.CustomUserDetailsService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private RestTemplate restTemplate;
    private String url;
    private CustomUserDetailsService userDetailsService;

//    @GetMapping("/login")
//    public AccountLoginDto getAccount() {
//        userDetailsService.loadUserByUsername(String )
//    }

    @PostMapping
    public List<AccountCreateDto> createAccount() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<AccountCreateDto>> exchange = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        return exchange.getBody();
    }
}
