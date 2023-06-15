package com.nhnacademy.minidooray.controller;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import com.nhnacademy.minidooray.service.AccountService;
import com.nhnacademy.minidooray.validator.EmailValidator;
import com.nhnacademy.minidooray.validator.IdValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    private final IdValidator idValidator;
    private final EmailValidator emailValidator;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(idValidator);
        binder.addValidators(emailValidator);
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "accounts/signup";
    }

    @PostMapping("/signup")
    public String createAccount(@Valid AccountCreateDto accountCreateDto, Errors errors, Model model) {
        String encodedPassword = passwordEncoder.encode(accountCreateDto.getPassword());
        accountCreateDto.setPassword(encodedPassword);

        if (errors.hasErrors()) {
            model.addAttribute("AccountCreateDto", accountCreateDto);

            Map<String, String> errorMap = accountService.accountValidator(errors);
            for (String key : errorMap.keySet()) {
                model.addAttribute(key, errorMap.get(key));
            }
            return "accounts/signup";
        }
        accountService.createAccount(accountCreateDto);

        return "redirect:/login";
    }

    // TODO 경로 바꾸기
    @GetMapping("/afterLogin")
    public String doAfterLogin() {
        return "afterLogin";
    }

}
