package com.example.servicealpha.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/service-alpha")
public class TestController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('view_demo')")
    public String testFunc() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "service-alpha. current user:" + auth.getName();
    }
}
