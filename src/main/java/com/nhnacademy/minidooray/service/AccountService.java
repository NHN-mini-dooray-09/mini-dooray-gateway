package com.nhnacademy.minidooray.service;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import com.nhnacademy.minidooray.adapter.AccountAdaptor;
import com.nhnacademy.minidooray.util.RequestAccountApi;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final RestTemplate restTemplate;
    private RequestAccountApi requestAccountApi;
    private final PasswordEncoder passwordEncoder;
    private final AccountAdaptor accountAdaptor;

    private final String URL = "http://localhost:8080";
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


    public Map<String, String> accountValidator(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            validatorResult.put(String.format("valid_%s", error.getField()), error.getDefaultMessage());
        }
        return validatorResult;
    }

        public boolean checkIdExist(String id) {
        return accountAdaptor.idExist(id);
    }
}
