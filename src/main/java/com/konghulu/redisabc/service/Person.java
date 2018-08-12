package com.konghulu.redisabc.service;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lining
 * @date 2018-08-12
 */
@Data
public class Person implements Serializable {

    public Person(){

    }

    public Person(Integer id, String name) {
	this.id = id;
	this.name = name;
    }

    private Integer id;

    private String name;

    @Override
    public String toString() {
	return String.format("id:%d,name:%s", this.id, this.name);
    }
}
