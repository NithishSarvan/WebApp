package com.nithi.webapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // web request
public class HelloController {

	// http://localhost:8080/say-hello

	@RequestMapping("say-hello") // handle "say-hello" particular request
	@ResponseBody // it return this method to browser
	public String sayHello() {
		return "Hi, this s reposnse, from @Controller";
	}
	
	@RequestMapping("say-hello-jsp") // handle "say-hello" particular request
	public String sayHelloJsp() {
		return "sayHello";
	}

}
	