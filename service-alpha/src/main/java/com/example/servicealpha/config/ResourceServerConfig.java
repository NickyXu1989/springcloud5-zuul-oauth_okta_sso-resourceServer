package com.example.servicealpha.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-server";

//    @Autowired
//    private UserInfoTokenServices userInfoTokenServices;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //配置不需要安全拦截url
                .antMatchers("/test/no_need_token").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();

    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(true);
//        userInfoTokenServices.setPrincipalExtractor(principalExtractor());
        // 配置了user-info-uri默认使用的就是userInfoTokenServices，这个这么配置只是为了设置principalExtractor
//        resources.tokenServices(userInfoTokenServices);
    }

    /**
     * 自定义Principal提取器，返回的Principal是一个map
     *
     * @return
     */
//    @Bean
//    public PrincipalExtractor principalExtractor() {
//        return new CustomizePrincipalExtractor();
//    }

}
