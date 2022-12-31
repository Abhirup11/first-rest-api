package com.abhirup.learning.springBoot.firstrestapi.helloWorld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

	@RequestMapping("/hello-world")
	public String helloWorld() {
		return "Hello-World";
	}
	
	
	@RequestMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@RequestMapping("/hello-world-path-param/{name}/message/{message}")
	public HelloWorldBean helloWorldPathParam(@PathVariable String name,
			@PathVariable String message) {
		return new HelloWorldBean("Hello World "+name+" Your msg is "+message);
	}
	
}
