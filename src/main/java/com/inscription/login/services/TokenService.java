package com.inscription.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void storeToken(String token, long ttlSeconds) {
        redisTemplate.opsForValue().set(token, "valid", ttlSeconds, TimeUnit.SECONDS);
    }

    public boolean isTokenValid(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }

    public void revokeToken(String token) {
        redisTemplate.delete(token);
    }
}
