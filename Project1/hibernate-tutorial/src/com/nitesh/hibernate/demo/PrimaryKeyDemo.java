package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
			
			System.out.println("Creating 3 Students object...");
			Student tempStudent1= new Student("Hariom","Tiwari","hari@gmail.com");
			Student tempStudent2= new Student("Lucky","Tiwari","lucky@gmail.com");
			Student tempStudent3= new Student("Pinku","Tiwari","pinku@gmail.com");

			
			session.beginTransaction();
			System.out.println("Saving the Student Object...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			session.getTransaction().commit();
			
			System.out.println("Done Transaction");
		}
		finally {
			factory.close();
		}
	}

}
