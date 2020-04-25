package com.nitesh.springboot.demo.mycoolapp.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
	@GetMapping("/")
	public String sayHello() {
		return "Hello World! Local time on the server is: "+ LocalDateTime.now();
	}
	
	//expose new endpoint for workout
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}
}
