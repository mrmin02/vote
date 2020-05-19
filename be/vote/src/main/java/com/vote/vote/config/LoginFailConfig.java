package com.vote.vote.config;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class LoginFailConfig implements AuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       
       
       request.setAttribute("fail", "아이디 또는 비밀번호가 잘못되었습니다.");
       
       request.getRequestDispatcher("/auth").forward(request, response);
       
    }
}