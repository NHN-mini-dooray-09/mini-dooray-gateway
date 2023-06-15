package com.nhnacademy.minidooray.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//public class LoginSuccessHandler implements AuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                  Authentication authentication) throws IOException, ServletException {
//        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(1000);
//
//        Cookie cookie = new Cookie("LOGIN", session.getId());
//        response.addCookie(cookie);
//        response.sendRedirect("/accounts/afterLogin");
//    }
//}

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());

        HttpSession session = request.getSession();
        session.setAttribute("username", userDetails.getUsername());
        session.setAttribute("authority", authorities.get(0).getAuthority());
    }
}