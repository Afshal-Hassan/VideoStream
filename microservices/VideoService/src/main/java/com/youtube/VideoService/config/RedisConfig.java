package com.youtube.VideoService.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableRedisRepositories
public class RedisConfig {


    @Value("${redis.host}")
    public String hostName;


    @Value("${redis.port}")
    public Integer port;


    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(hostName);
        configuration.setPort(port);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration);

        JedisPoolConfig poolConfiguration = new JedisPoolConfig();
        poolConfiguration.setMaxTotal(10);
        poolConfiguration.setMaxIdle(5);
        poolConfiguration.setMinIdle(1);

        jedisConnectionFactory.setPoolConfig(poolConfiguration);

        return jedisConnectionFactory;
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }
}
