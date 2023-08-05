package com.youtube.auth.service.AuthService.beans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderBean {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(Integer.parseInt("10"));
    }
}
