package com.example.oauth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AuthUserDetailsService userDetailsService;
    @Bean
    public UserDetailsService userDetailsService(){
        return new AuthUserDetailsService();
    }


//    @Autowired
//    private UmAuthenticationProvider umAuthenticationProvider;



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("httpSecurity configure");
        http
                .anonymous()
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                ;
//        http.requestMatchers().antMatchers("/login", "/oauth/authorize").and().authorizeRequests().anyRequest()
//                .authenticated().and().formLogin().permitAll().and().csrf().disable().cors().disable();

    }

    //不需要认证的url
    @Override
    public void configure(final WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/error**");
        webSecurity.ignoring().antMatchers("/actuator/**");
        webSecurity.ignoring().antMatchers("/test/*");
        webSecurity.ignoring().antMatchers("/getUser");
//        webSecurity.ignoring().antMatchers("/oauth/**");
//        webSecurity.ignoring().antMatchers("/user");
//        webSecurity.ignoring().antMatchers("/user");
//        webSecurity.ignoring().antMatchers("/api/v1/tsdata/tsPushData");
//        webSecurity.ignoring().antMatchers("/api/tsPushData");
//        webSecurity.ignoring().antMatchers("/api/v2/test/**");
//        webSecurity.ignoring().antMatchers("/api/v1/tsdata/*");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
                .userDetailsService(userDetailsService())
                .passwordEncoder(new MyPasswordEncoder());

//        authenticationManagerBuilder
//                .authenticationProvider(umAuthenticationProvider);
    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
