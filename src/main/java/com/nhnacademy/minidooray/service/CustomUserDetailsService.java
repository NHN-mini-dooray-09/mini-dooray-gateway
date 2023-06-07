package com.nhnacademy.minidooray.service;

import com.nhnacademy.minidooray.entity.AccountDao;
import com.nhnacademy.minidooray.entity.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    // TODO 원래는 repository로 직접 DB와 연결했는데 accountApi에 어떻게 연결하지?
    // RestTemplate으로 accountApi를 호출할 service를 만들어
    //
//    private final AccountDao accountDao;
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    // TODO accountApi의 account의 id값과 gateway에서 비교할 id값이 같은지 같지 않은지 비교하는 메서드?
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AccountGateway accountGateway = accountDao.
        // 1. service
        // 2. userInfo
        // 3. return new User(userInfo.getId((), get())
        return new User("admin", passwordEncoder.encode("admin"), List.of());


    }
}
