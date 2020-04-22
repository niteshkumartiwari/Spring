package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Course;
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
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create session
		Session session= factory.getCurrentSession();
		
		try {
			
			
			session.beginTransaction();
			
			//Create Instructor
			int theId=3;
			
			//Everything is loaded(instructor,instructor_details,courses) all because of the EAGER loading.
			//No need to hit the DB again for the subsequent calls.
			Instructor theInstructor= session.get(Instructor.class, theId);
			System.out.println(theInstructor);
			
			//calling the getter method while session is still open
			//Now data is in the memory
			System.out.println("Courses: "+ theInstructor.getCourses());

			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			//since courses are loaded lazily..this should fail
			//lazy-Data
			System.out.println("Courses: "+ theInstructor.getCourses());
			
			System.out.println("Done Transaction");
		}
		finally {
			factory.close();
		}
	}
}
