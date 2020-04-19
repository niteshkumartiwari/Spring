package com.nitesh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		//config file
		ClassPathXmlApplicationContext context= 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get bean
		Coach theCoach= context.getBean("tennisCoach",Coach.class);
		Coach alphaCoach= context.getBean("tennisCoach",Coach.class);
		
		if(theCoach== alphaCoach)
			System.out.println("Are same");
		else
			System.out.println("Are different");
		
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		//close bean
		context.close();
	}

}
