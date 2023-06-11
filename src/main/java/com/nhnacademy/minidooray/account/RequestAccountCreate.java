package com.nhnacademy.minidooray.account;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RequestAccountCreate {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;
}
