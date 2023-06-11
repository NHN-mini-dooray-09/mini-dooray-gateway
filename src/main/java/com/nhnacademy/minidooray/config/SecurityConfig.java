package com.nhnacademy.minidooray.config;

import com.nhnacademy.minidooray.auth.LoginSuccessHandler;
//import com.nhnacademy.minidooray.service.CustomO2Auth2MemberService;
import com.nhnacademy.minidooray.service.CustomO2Auth2MemberService;
import com.nhnacademy.minidooray.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
//@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomO2Auth2MemberService customO2Auth2MemberService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeRequests()
                    .antMatchers("/**").permitAll()
                .and()
                .csrf()
                    .disable()
                .formLogin()
//                    .loginPage("/accounts/login").permitAll()
//                    .usernameParameter("id")
//                    .passwordParameter("pw")
//                .userInfoEndpoint()
//                .userService(customO2Auth2MemberService)
                .and()
                .oauth2Login()
//                    .loginPage("/accounts/login").permitAll()
//                    .authorizationEndpoint()
//                    .baseUri("/login")
                    .userInfoEndpoint()
                    .userService(customO2Auth2MemberService)
                .and()
                .and()
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginSuccessHandler();
    }

    // 인증 전의 Authetication 객체를 받아와 인증이 완료된 객체를 반환하는 역할
    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

}
