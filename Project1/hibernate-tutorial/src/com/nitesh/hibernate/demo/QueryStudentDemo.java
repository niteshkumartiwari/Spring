package com.nitesh.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nitesh.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	public static void main(String[] args) {
		//create Session factory
		SessionFactory factory= new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session= factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//query students
			List<Student> theStudents= session.createQuery("from Student").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students firstName="Nitesh"
			System.out.println("\nStudents whose name is Nitesh?");
			theStudents= session.createQuery("from Student s where s.firstName='Nitesh'").getResultList();
			
			displayStudents(theStudents);
			
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents)
			System.out.println(tempStudent.toString());
	}
}
