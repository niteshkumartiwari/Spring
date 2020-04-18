package com.nitesh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		// load configuration file
		ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		//get Beans
		Coach theCoach= context.getBean("myCoach",Coach.class);
		
		Coach alphaCoach= context.getBean("myCoach",Coach.class);

		//check if they are same
		
		boolean result= (theCoach==alphaCoach);
		System.out.println(result);
		System.out.println("memory location for theCoach:"+theCoach);
		System.out.println("memory location for alphaCoach:"+alphaCoach);

		
		//close context
		context.close();
	}

}
