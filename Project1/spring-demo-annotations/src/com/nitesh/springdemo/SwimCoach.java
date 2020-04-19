package com.nitesh.springdemo;

public class SwimCoach implements Coach {
	
	private FortuneService fortuneService;

	public SwimCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Swim 1000m as a warm-up.";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
