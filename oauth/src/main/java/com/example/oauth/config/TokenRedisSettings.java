package com.example.oauth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "token.redis")
@Data
public class TokenRedisSettings {
    private String host;
    private int port;
    private String password;
    private int database;
    private int timeout;
}
