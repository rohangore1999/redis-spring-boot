package com.api.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean // so that spring will manage this object - part for Dependency injection
    public RedisConnectionFactory connectionFactory(){
//        RedisConnectionFactory that uses Lettuce to connect to Redis. Lettuce is one of the clients that you can use to interact with Redis in Java.
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // connection Factory
        redisTemplate.setConnectionFactory(connectionFactory());

        // Key serializer (while storing key, will store in string format)
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // Value serializer (while storing Value, will store in string format)
        // GenericJackson2JsonRedisSerializer -> to convert from JSON <--> string
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }
}
