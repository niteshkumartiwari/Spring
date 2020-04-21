package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Student;

public class CreateDemo {
	public static void main(String[] args) {
		//create Session factory
		SessionFactory factory= new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create session
		Session session= factory.getCurrentSession();
		
		try {
			
			Instructor theInstructor= new Instructor("Shalini","Gupta","shalini@gmail.com");
			InstructorDetail theInstructorDetail= new InstructorDetail("http://abc.com/youtube","painting");
			
			theInstructor.setInstructorDetail(theInstructorDetail);
			session.beginTransaction();
			System.out.println("Saving instructor: "+ theInstructor);
			session.save(theInstructor);
			session.getTransaction().commit();
			
			System.out.println("Done Transaction");
		}
		finally {
			factory.close();
		}
	}
}
