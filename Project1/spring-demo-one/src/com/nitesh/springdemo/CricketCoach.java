package com.nitesh.springdemo;

public class CricketCoach implements Coach{
	private FortuneService fortuneService;
	
	private String emailAddress;
	private String team;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

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
