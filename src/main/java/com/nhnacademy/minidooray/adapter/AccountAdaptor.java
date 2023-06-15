package com.nhnacademy.minidooray.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AccountAdaptor {
    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8080";

    public boolean idExist(String id) {
        String uri = "/accounts/checkId/";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(id, httpHeaders);
        ResponseEntity<Boolean> response = restTemplate.exchange(
                URL + uri + id,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public boolean emailExist(String email) {
        String uri = "/accounts/checkEmail/";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(email, httpHeaders);
        ResponseEntity<Boolean> response = restTemplate.exchange(
                URL + uri + email,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
