package com.estsoft.springproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserViewController {
    // GET /login  -> login 페이지 연결
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }
    // GET /signup -> 회원가입 페이지 연결
    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @GetMapping("/logout")
    public String logout
            (HttpServletRequest request,
             HttpServletResponse response
            ) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/user/login";
    }
}