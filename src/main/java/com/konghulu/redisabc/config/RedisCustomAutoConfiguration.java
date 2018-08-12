package com.konghulu.redisabc.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.net.UnknownHostException;

/**
 * @author lining
 * @date 2018-08-12
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCustomAutoConfiguration {

    @Bean
    public RedisTemplate<String, Serializable> commonRedisTemplate(
	    RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
	RedisTemplate<String, Serializable> template = new RedisTemplate<>();
	template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
	template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
	template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
	template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

	template.setConnectionFactory(redisConnectionFactory);
	return template;
    }
}
