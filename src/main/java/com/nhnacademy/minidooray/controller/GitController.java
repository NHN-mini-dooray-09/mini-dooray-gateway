package com.nhnacademy.minidooray.controller;

import com.nhnacademy.minidooray.entity.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Setter
public class GitController {
    RestTemplate restTemplate;

    @GetMapping
    public List<AccountDto> getGitAccount() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/vnd.github+json");
        httpHeaders.add("Authorization", "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");
        httpHeaders.add("X-GitHub-Api-Version", "2022-11-28");

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<AccountDto>> exchange = restTemplate.exchange(
                "https://api.github.com/user/emails",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        return exchange.getBody();
    }
}
