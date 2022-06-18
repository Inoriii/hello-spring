package com.inoriii.hello.spring.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author sakura
 * @date: 2021/8/2 23:11
 * @description:
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<? extends Serializable, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
//        JdkSerializationRedisSerializer redisSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //key序列化
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(redisSerializer);
        //value hashmap序列化
        template.setHashKeySerializer(redisSerializer);
        //key hashmap序列化
        template.setHashValueSerializer(redisSerializer);
        return template;
    }
}
