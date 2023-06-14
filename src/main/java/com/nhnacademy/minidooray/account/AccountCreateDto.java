package com.nhnacademy.minidooray.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountCreateDto {
    private String id;
    private String password;
    private String email;
    private String name;
//    private String status;
//    private String role;
    private LocalDate date;
}
