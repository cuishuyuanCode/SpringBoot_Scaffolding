package com.csy.demo.config;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisUtil {



    @SuppressWarnings("unchecked")
    private static RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate");

    //存入Redis，并指定时长
    public static void put(String key,String value,int expireTime){
        redisTemplate.opsForValue().set(key,value,expireTime,TimeUnit.SECONDS);
    }

    //存入Redis，并指定时长
    public static void put(String key,Object value,int expireTime){
        redisTemplate.opsForValue().set(key,value,expireTime,TimeUnit.SECONDS);
    }

    public static void put(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public static void put(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    public static String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getList(String key, Class<T> clazz) {
        return (List<T>) redisTemplate.opsForValue().get(key);
    }

    public static boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public static void delete(String key) {
        redisTemplate.delete(key);
    }
}
