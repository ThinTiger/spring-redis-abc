package com.konghulu.redisabc.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;

@RestController
@RequestMapping("/redis")
@Configuration
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisLockRegistry redisLockRegistry;

    @RequestMapping("/lock")
    public Boolean testLock(){
        Lock lock = redisLockRegistry.obtain("hululock");
        try{
            if (lock.tryLock()){
                System.out.println("testlock!~");
                Thread.sleep(5000);
            } else {
                System.out.println("获取锁失败！~");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
        return true;
    }
}
