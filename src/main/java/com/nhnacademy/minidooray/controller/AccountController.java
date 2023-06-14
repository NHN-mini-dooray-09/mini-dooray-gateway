package com.nhnacademy.minidooray.controller;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import com.nhnacademy.minidooray.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "accounts/signup";
    }

    @PostMapping("/signup")
    public String createAccount(AccountCreateDto accountCreateDto) {
        String encodedPassword = passwordEncoder.encode(accountCreateDto.getPassword());
        accountCreateDto.setPassword(encodedPassword);
        customUserDetailsService.createAccount(accountCreateDto);

        return "redirect:/login";
    }

    // TODO 경로 바꾸기
    @GetMapping("/afterLogin")
    public String doAfterLogin() {
        return "afterLogin";
    }

//    @GetMapping("/exists/{id}")
}
