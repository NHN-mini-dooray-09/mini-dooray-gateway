package com.nhnacademy.minidooray;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import com.nhnacademy.minidooray.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApplicationRunner {
    private final CustomUserDetailsService customUserDetailsService;
    @EventListener(ApplicationReadyEvent.class)
    public void createAdminAccount() throws IOException {
//        customUserDetailsService.loadUserByUsername("admin").;

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        AccountCreateDto accountCreateDto = new AccountCreateDto();
        accountCreateDto.setId("admin");
        accountCreateDto.setPassword(bCryptPasswordEncoder.encode("admin"));
        accountCreateDto.setEmail("admin@mail.com");
        accountCreateDto.setName("admin");

        customUserDetailsService.createAccount(accountCreateDto);
    }
}