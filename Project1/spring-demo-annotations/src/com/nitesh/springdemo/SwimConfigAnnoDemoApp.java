package com.nitesh.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimConfigAnnoDemoApp {

	public static void main(String[] args) {
		//config file
		AnnotationConfigApplicationContext context= 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		//get bean
		SwimCoach theCoach= context.getBean("swimCoach",SwimCoach.class);
		
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		System.out.println(theCoach.getEmail());
		System.out.println(theCoach.getTeam());
		
		//close bean
		context.close();
	}

}
