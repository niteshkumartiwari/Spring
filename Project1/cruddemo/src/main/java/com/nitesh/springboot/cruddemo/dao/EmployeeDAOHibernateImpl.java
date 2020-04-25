package com.nitesh.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nitesh.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	//define field for entity manager
	private EntityManager entityManager;
	
	//setup constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		this.entityManager=theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		//get the current hibernate session
		Session currentSession= entityManager.unwrap(Session.class);
		
		//create query
		Query<Employee> theQuery= currentSession.createQuery("from Employee",Employee.class);
		
		//execute query and get result list
		List<Employee> employees= theQuery.getResultList();
		
		//return results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get the current session
		Session currentSession= entityManager.unwrap(Session.class);
		
		//get the employee
		Employee theEmployee= currentSession.get(Employee.class, theId);
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//get the current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		//get the current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create query
		Query theQuery= currentSession.createQuery("delete from Employee where id=:theEmpId");
		theQuery.setParameter("theEmpId", theId);
				
		theQuery.executeUpdate();
	}

}
