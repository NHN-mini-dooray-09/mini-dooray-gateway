package com.nhnacademy.minidooray.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountDto {
    //    @Column(name = "account_seq")
//    private Long seq;
    private String id;
    //    private String name;
    private String password;
//    private String email;
}
