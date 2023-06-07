package com.nhnacademy.minidooray.util;

import com.nhnacademy.minidooray.entity.AccountGit;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
@RequiredArgsConstructor
public class RequestGitApi {
    private final RestTemplate restTemplate;

    public List<AccountGit> getResponse(String url, HttpMethod httpMethod, HttpHeaders httpHeaders,
                                        ParameterizedTypeReference<List<AccountGit>> reference) {
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<AccountGit>> exchange = restTemplate.exchange(
                url,
                httpMethod,
                requestEntity,
                reference
        );
        return exchange.getBody();
    }
}
