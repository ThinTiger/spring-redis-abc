package com.konghulu.redisabc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.konghulu.**")
public class KonghuluRedisabcApplication {

    public static void main(String[] args){
	SpringApplication.run(KonghuluRedisabcApplication.class);
    }
}
