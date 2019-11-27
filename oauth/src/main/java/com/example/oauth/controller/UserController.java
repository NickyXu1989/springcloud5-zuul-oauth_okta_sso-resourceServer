package com.example.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/user")
    public Principal getUser(Principal principal){
        System.out.println(principal.toString());
        return principal;
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth);
//        return null;
    }

    @GetMapping("/whoami")
    public String whoami(@AuthenticationPrincipal(expression="name") String name) {
        return name;
    }
}