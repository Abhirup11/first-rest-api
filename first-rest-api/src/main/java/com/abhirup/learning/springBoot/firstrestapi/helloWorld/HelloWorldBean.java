package com.abhirup.learning.springBoot.firstrestapi.helloWorld;

public class HelloWorldBean {

	private String message;

	public String getMessage() {
		return message;
	}

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
	
}
