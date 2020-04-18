package com.nitesh.springdemo;

public class TrackCoach implements Coach {
	FortuneService fortuneService;
	
	public TrackCoach(FortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run Hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
	//add an init method
	public void initMethod() {
		System.out.println("inside TrackCoach:initMethod");
	}
	
	//add a destroy method
	public void destroyMethod() {
		System.out.println("inside TrackCoach:destroyMethod");
	}
}
