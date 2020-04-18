package com.nitesh.springdemo;

public class CricketCoach implements Coach{
	FortuneService fortuneService;

	public CricketCoach(FortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Do batting and bowling for 4 hrs straight";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
