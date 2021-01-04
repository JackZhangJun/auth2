package com.zhangjun;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringAuth2AuthorizationApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void test() {
        System.out.println("haha");
        System.out.println("password:"+passwordEncoder.encode("client2"));

    }

}
