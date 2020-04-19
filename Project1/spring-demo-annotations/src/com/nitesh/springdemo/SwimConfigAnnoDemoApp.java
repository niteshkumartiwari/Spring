package com.nitesh.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimConfigAnnoDemoApp {

	public static void main(String[] args) {
		//config file
		AnnotationConfigApplicationContext context= 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		//get bean
		Coach theCoach= context.getBean("swimCoach",Coach.class);
		
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		//close bean
		context.close();
	}

}
