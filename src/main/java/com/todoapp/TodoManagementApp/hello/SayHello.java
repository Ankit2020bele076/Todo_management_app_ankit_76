package com.todoapp.TodoManagementApp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHello {
	
	@RequestMapping("/sayhello")
	//@ResponseBody
	public String sayhello() {
		return "sayHello";
	}
}
