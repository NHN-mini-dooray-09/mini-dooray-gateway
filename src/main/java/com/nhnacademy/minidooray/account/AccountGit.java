package com.nhnacademy.minidooray.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountGit {
    private String email;
    private Boolean primary;
    private Boolean verified;
    private String visibility;
}
