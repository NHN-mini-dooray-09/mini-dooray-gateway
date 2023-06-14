package com.nhnacademy.minidooray.controller;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/git")
@RequiredArgsConstructor
public class GitController {
    private final RestTemplate restTemplate;

    @Value("${github.token}")
    private String githubToken;

    @GetMapping
    public List<AccountCreateDto> getGitAccount() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/vnd.github+json");
        httpHeaders.setBearerAuth(githubToken);
        httpHeaders.add("X-GitHub-Api-Version", "2022-11-28");

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<AccountCreateDto>> exchange = restTemplate.exchange(
                "https://api.github.com/user/emails",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        return exchange.getBody();
    }
}
