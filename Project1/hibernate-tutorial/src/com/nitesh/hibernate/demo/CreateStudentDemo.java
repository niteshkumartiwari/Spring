package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class CreateStudentDemo {
	public static void main(String[] args) {
		//create Session factory
		SessionFactory factory= new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session= factory.getCurrentSession();
		
		try {
			//use session object to save the Java 	object
			
			System.out.println("Creating new Student object...");
			Student tempStudent= new Student("Nitesh","Tiwari","abc@gmail.com");
			
			session.beginTransaction();
			System.out.println("Saving the Student Object...");
			session.save(tempStudent);
			
			session.getTransaction().commit();
			
			System.out.println("Done Transaction");
		}
		finally {
			factory.close();
		}
	}
}
