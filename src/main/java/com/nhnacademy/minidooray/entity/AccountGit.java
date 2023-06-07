package com.nhnacademy.minidooray.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountGit {
    private String email;
    private Boolean primary;
    private Boolean verified;
    private String visibility;
}
