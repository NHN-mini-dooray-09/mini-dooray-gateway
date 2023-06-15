package com.nhnacademy.minidooray.validator;

import com.nhnacademy.minidooray.account.AccountCreateDto;
import com.nhnacademy.minidooray.adapter.AccountAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class IdValidator extends AbstractValidator<AccountCreateDto> {
    private final AccountAdaptor accountAdaptor;
    @Override
    protected void doValidate(AccountCreateDto dto, Errors errors) {
        if (accountAdaptor.idExist(dto.getId())) {
            errors.rejectValue("id", "아이디 중복 오류", "이미 사용 중인 아이디입니다.");
        }
    }
}
