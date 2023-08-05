package com.youtube.auth.service.AuthService.beans;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperBean {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
