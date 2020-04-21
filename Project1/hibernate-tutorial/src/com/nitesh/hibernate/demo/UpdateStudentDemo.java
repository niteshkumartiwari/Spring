package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	public static void main(String[] args) {
		//create Session factory
		SessionFactory factory= new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session= factory.getCurrentSession();
		
		try {			
			//now get a new session and start transaction
			session= factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on id: primary key
			int studentId=1;
			Student theStudent= session.get(Student.class,studentId);
			System.out.println(theStudent.toString());
			
			System.out.println("Updating student...");
			theStudent.setFirstName("Happy");
			
			//commit the transaction
			session.getTransaction().commit();
			
			//NEW CODE
			
			session= factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("updating email address for hariom");
			
			session.createQuery("update Student set email='hariom@gmail.com' where firstName='Hariom'").executeUpdate();
			
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}
}
