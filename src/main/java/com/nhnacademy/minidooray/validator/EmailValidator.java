package com.nhnacademy.minidooray.validator;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import com.nhnacademy.minidooray.adapter.AccountAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class EmailValidator extends AbstractValidator<AccountCreateDto> {
    private final AccountAdaptor accountAdaptor;
    @Override
    protected void doValidate(AccountCreateDto dto, Errors errors) {
        if (accountAdaptor.emailExist(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용 중인 이메일입니다.");
        }
    }
}
