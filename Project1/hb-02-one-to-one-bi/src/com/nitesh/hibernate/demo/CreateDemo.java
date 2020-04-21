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
			
			
			session.beginTransaction();
			
			int theId=1;
			Instructor theInstructor= session.get(Instructor.class, theId);
			
			System.out.println("Found Instructor: "+ theInstructor);
			
			//delete the instructor
			if(theInstructor!=null) {
				System.out.println("Deleting Instructor "+ theInstructor.getFirstName());
				
				//Note: Also delete associated instructor_details object since cascading is ON
				session.delete(theInstructor);
			}
			
			session.getTransaction().commit();
			System.out.println("Done Transaction");
		}
		finally {
			factory.close();
		}
	}
}
