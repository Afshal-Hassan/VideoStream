package com.youtube.VideoService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;



@Configuration
@EnableR2dbcRepositories(basePackages = "com.youtube.VideoService.repos")
public class RepositoriesConfig {
}
