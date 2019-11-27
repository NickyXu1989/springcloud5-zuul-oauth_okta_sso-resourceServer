package com.example.servicealpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
//@EnableDiscoveryClient
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServiceAlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAlphaApplication.class, args);
	}

}

