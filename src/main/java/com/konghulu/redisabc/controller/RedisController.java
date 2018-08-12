package com.konghulu.redisabc.controller;

import com.konghulu.redisabc.service.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;

@RestController
@RequestMapping("/redis")
@Configuration
public class RedisController {

    private static final String MOGIC_STR = "do not own";

    Person personA;

    Person personB;

    public RedisController() {
	personA = new Person(1, "小明");
	personB = new Person(1, "小华");
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisLockRegistry redisLockRegistry;

    @Resource
    private RedisTemplate<String, Serializable> commonRedisTemplate;

    @RequestMapping("/lock")
    public Boolean testLock() {
	Lock lock = redisLockRegistry.obtain("hululock");
	try {
	    if (lock.tryLock()) {
		System.out.println("testlock!~");
		Thread.sleep(5000);
	    } else {
		System.out.println("获取锁失败！~");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	    //这里这么写也太丑了吧
	    try {
		lock.unlock();
	    } catch (IllegalStateException ex) {
		if (ex.getMessage().contains(MOGIC_STR)) {
		    System.out.println("debug!~~~ do not own lock");
		} else {
		    System.out.println("error!~~~");
		}
	    }
	}
	return true;
    }

    @RequestMapping("/list")
    public Boolean testList() {
	ListOperations<String, Serializable> listOperations = commonRedisTemplate.opsForList();
	listOperations.rightPush("testList", personA);
	listOperations.rightPush("testList", personB);

	Person person = (Person) listOperations.rightPop("testList");
	System.out.println(person.toString());

	return true;
    }

    @RequestMapping("/set")
    public Boolean testSet() {
	SetOperations<String, Serializable> setOperations = commonRedisTemplate.opsForSet();
	setOperations.add("testSet", personA, personB);

	Person person = (Person) setOperations.pop("testSet");
	System.out.println(person.toString());
	return true;
    }

    @RequestMapping("/hash")
    public Boolean testHash() {
	HashOperations<String, String, String> hashOperations = commonRedisTemplate.opsForHash();
	hashOperations.put("personA_hash", "id", personA.getId().toString());
	hashOperations.put("personA_hash", "name", personA.getName());
	hashOperations.put("personB_hash", "id", personB.getId().toString());
	hashOperations.put("personB_hash", "name", personB.getName());

	hashOperations.put("personB_hash", "name", personB.getName() + "_edited");
	System.out.println(hashOperations.get("personB_hash", "name"));
	return true;
    }

    @RequestMapping("/zset")
    public Boolean testZset() {
	return true;
    }
}
