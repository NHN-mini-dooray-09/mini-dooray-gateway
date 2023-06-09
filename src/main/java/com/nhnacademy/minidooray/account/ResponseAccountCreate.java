package com.nhnacademy.minidooray.account;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseAccountCreate {
    private String id;
    private String password;
    private String email;
    private String name;
}
