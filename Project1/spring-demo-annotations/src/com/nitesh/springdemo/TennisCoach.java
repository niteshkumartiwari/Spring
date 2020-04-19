package com.nitesh.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

//@Component("thatSillyCoach")
@Component
@Scope("prototype")
public class TennisCoach implements Coach {
	@Autowired
	private FortuneService fortuneService;
	
//	@Autowired
//	public TennisCoach(FortuneService fortuneService) {
//		super();
//		this.fortuneService = fortuneService;
//	}
	
	public TennisCoach() {
		System.out.println("inside TennisCoach: no-args constructor");
	}
	
//	@Autowired
//	public void setFortuneService(FortuneService fortuneService) {
//		this.fortuneService = fortuneService;
//	}

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
	@PostConstruct
	public void init() {
		System.out.println("TennisCoach: init()");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("TennisCoach: destroy()");
	}

}
