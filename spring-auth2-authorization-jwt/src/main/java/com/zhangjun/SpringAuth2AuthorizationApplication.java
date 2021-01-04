package com.zhangjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SpringAuth2AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuth2AuthorizationApplication.class, args);
    }

}
