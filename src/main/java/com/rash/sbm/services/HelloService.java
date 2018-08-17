package com.rash.sbm.services;

import org.springframework.stereotype.Component;

@Component
public class HelloService {

	public String getHello() {
		return "Hello World from HelloService!";
	}
	
}
