/****************************************************************************
 *
 *                   Health Suite China
 *                © Koninklijke Philips N.V., 2019.
 *
 * All rights are reserved. Reproduction or dissemination
 * in whole or in part is prohibited without the written
 * consent of the copyright owner.
 *
 *
 * FILE NAME: RedisConfig.java
 *
 * CREATED: Oct 31, 2019 8:56:48 PM
 *
 * ORIGINAL AUTHOR(S): qixiao
 *
 ***************************************************************************/
package com.csy.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

@Configuration
public class RedisConfig {
    private RedisConnectionFactory factory;

    @Autowired
    public void setFactory(RedisConnectionFactory factory) {
        Assert.notNull(factory, "RedisConnectionFactory cannot be null");
        this.factory = factory;
    }

    @Bean
    @Qualifier("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
