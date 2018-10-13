package com.konghulu.redisabc.konghuluredisabc;

import com.konghulu.redisabc.server.KonghuluRedisabcApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KonghuluRedisabcApplication.class)
public class KonghuluRedisabcApplicationTests {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedisConnect() {
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		valueOperations.set("testKey", "testValue");
		System.out.println(valueOperations.get("testKey"));
	}

}
