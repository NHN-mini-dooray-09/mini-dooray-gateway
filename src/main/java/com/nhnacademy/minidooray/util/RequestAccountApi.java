package com.nhnacademy.minidooray.util;

import com.nhnacademy.minidooray.account.AccountCreateDto;
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
public class RequestAccountApi {
    private final RestTemplate restTemplate;

    public List<AccountCreateDto> getResponse(String url, HttpMethod httpMethod, HttpHeaders httpHeaders,
                                              ParameterizedTypeReference<List<AccountCreateDto>> reference) {
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<AccountCreateDto>> exchange = restTemplate.exchange(
                url,
                httpMethod,
                requestEntity,
                reference
        );
        return exchange.getBody();
    }
}
