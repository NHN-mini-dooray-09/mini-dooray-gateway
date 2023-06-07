package com.nhnacademy.minidooray.service;

import com.nhnacademy.minidooray.entity.AccountGit;
import com.nhnacademy.minidooray.entity.AccountGateway;
import com.nhnacademy.minidooray.util.RequestGitApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomO2Auth2MemberService extends DefaultOAuth2UserService {

//    @Value("${github.token}")
    private String githubToken;

    RestTemplate restTemplate;

    private final RequestGitApi requestGitApi;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = loadUser(userRequest);

        //TODO github의 email이 기존 유저의 email과 같으면 연동, 아니면 새로 가입
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/vnd.github+json");
        httpHeaders.add("Authorization", githubToken);
        httpHeaders.add("X-GitHub-Api-Version", "2022-11-28");

//        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        List<AccountGit> response = requestGitApi.getResponse(
                "https://api.github.com/user/emails",
                HttpMethod.GET,
                httpHeaders,
                new ParameterizedTypeReference<>() {
                }
        );
        return null;
    }

}
