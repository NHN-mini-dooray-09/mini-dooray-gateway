package com.nhnacademy.minidooray.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDao {
//    @Column(name = "account_seq")
    private Long seq;
    private String name;
    private String password;
    private String email;
}
