package com.nitesh.springdemo;

public class CricketCoach implements Coach{
	FortuneService fortuneService;

	public CricketCoach() {
		System.out.println("CricketCoach: inside no-args constructor");
	}

	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("CricketCoach: inside setter method");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Do batting and bowling for 4 hrs straight";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune()	;
	}

}
