package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class ReadStudentDemo {
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
			Student tempStudent= new Student("Prabhat","Tiwari","prabhat@gmail.com");
			
			session.beginTransaction();
			System.out.println("Saving the Student Object...");
			session.save(tempStudent);
			
			session.getTransaction().commit();
			
			System.out.println("Object saved @idx: "+tempStudent.getId());
			System.out.println("Done Transaction");
			
			//now get a new session and start transaction
			session= factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on id: primary key
			Student theStudent= session.get(Student.class,tempStudent.getId());
			System.out.println(theStudent.toString());
			
			//commit the transaction
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}
}
