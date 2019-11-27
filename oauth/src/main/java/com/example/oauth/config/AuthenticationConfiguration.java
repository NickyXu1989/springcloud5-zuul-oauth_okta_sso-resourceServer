package com.example.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//
//@Configuration
//public class AuthenticationConfiguration extends
//        GlobalAuthenticationConfigurerAdapter {
//
//    @Autowired
//    private AuthUserDetailsService userDetailsService;
//    @Autowired
//    private MyPasswordEncoder passwordEncoder;
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(
//                passwordEncoder);
//    }
//
//}