package com.example.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

//    @Autowired
//    private RedisConnectionFactory connectionFactory;

//    @Autowired
//    private RedisConnectionFactory connectionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userDetailsService")
    private  AuthUserDetailsService authUserDetailsService;

    @Autowired
    TokenRedisSettings tokenRedisSettings;



    //配置oauth2 client
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
//        clients.inMemory()
//                .withClient("android")//客户端ID
////                .authorizedGrantTypes("client_credentials", "password", "refresh_token", "implicit")
//                .authorizedGrantTypes("password", "refresh_token")
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//                .scopes("read", "write", "trust")//授权用户的操作权限
////                .secret("{noop}secret")//密码
//                .secret("{noop}android")//密码
//                .accessTokenValiditySeconds(3000)//token有效期为3000秒
//                .refreshTokenValiditySeconds(36000);//刷新token有效期为36000秒


        clients.inMemory()
                .withClient("android")
                .scopes("xx")
                .secret("android")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .and()
                .withClient("open-api")
                .scopes("xx")
                .authorizedGrantTypes("implicit");
    }

    //配置token存储在redis
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer)throws Exception{
        endpointsConfigurer
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(authUserDetailsService);
//        endpointsConfigurer.authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService);
    }




    @Bean
    public TokenStore tokenStore(){
//        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
//        return redis;
        return new RedisTokenStore(jedisConnectionFactory());
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(tokenRedisSettings.getHost());
        redisStandaloneConfiguration.setPort(tokenRedisSettings.getPort());
        redisStandaloneConfiguration.setPassword(tokenRedisSettings.getPassword());
        redisStandaloneConfiguration.setDatabase(tokenRedisSettings.getDatabase());

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(tokenRedisSettings.getTimeout()));
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());

        return factory;

    }

//    @Bean
//    public TokenStore tokenStore(){
//        InMemoryTokenStore tokenStore = new InMemoryTokenStore();
//        return tokenStore;
//    }




    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) throws Exception{
        securityConfigurer.allowFormAuthenticationForClients();
        securityConfigurer.allowFormAuthenticationForClients();
        securityConfigurer.checkTokenAccess("permitAll()");
    }


    //cros
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


}
