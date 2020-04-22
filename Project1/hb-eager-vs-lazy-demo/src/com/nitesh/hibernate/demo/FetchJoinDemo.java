package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.nitesh.hibernate.demo.entity.Course;
import com.nitesh.hibernate.demo.entity.Instructor;
import com.nitesh.hibernate.demo.entity.InstructorDetail;
import com.nitesh.hibernate.demo.entity.Student;

public class FetchJoinDemo {
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
			
			//Adding HQL for lazy issue
			Query<Instructor> query= session.createQuery("select i from Instructor i "
														+ "JOIN FETCH i.courses "
														+ "where i.id=:theInstructor",
														Instructor.class);
			//set query parameter
			query.setParameter("theInstructor", theId);
			
			//execute query and get the instructor
			Instructor tempInstructor= query.getSingleResult();
			
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			//since courses are loaded lazily..this should fail
			//lazy-Data
			System.out.println("Instructor: "+ tempInstructor);
			System.out.println("Courses: "+ tempInstructor.getCourses());
			
			System.out.println("Done Transaction");
		}
		finally {
			factory.close();
		}
	}
}
