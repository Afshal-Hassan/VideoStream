package com.youtube.VideoService.config;


import com.youtube.VideoService.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.Collections;

@Configuration
@EnableCaching
public class CacheConfig {


    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;


    @Bean
    public CacheManager cacheManager() {
        return RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                .withInitialCacheConfigurations(Collections.singletonMap(AppConstants.CACHE_NAME, RedisCacheConfiguration.defaultCacheConfig()))
                .build();
    }
}
