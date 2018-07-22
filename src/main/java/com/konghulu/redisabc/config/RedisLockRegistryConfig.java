package com.konghulu.redisabc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
public class RedisLockRegistryConfig {

    private static Logger logger = LoggerFactory.getLogger(RedisLockRegistryConfig.class);

    private static final String PRE_CACHE_LOCK = "cache_lock";

    @Bean
    public RedisLockRegistry createRedisLock(RedisConnectionFactory factory) {
	return new RedisLockRegistry(factory, PRE_CACHE_LOCK);
    }
}