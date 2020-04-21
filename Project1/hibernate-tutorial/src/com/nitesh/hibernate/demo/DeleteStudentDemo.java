package com.nitesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
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
			int studentId=1;
			
			session.beginTransaction();
			
//			Student theStudent= session.get(Student.class,studentId);
//			
//			//delete the student
//			session.delete(theStudent);
			
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done Transaction");
		}
		finally {
			factory.close();
		}
	}
}
