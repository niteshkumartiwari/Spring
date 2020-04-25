package com.nitesh.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nitesh.springboot.cruddemo.dao.EmployeeRepository;
import com.nitesh.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceIml implements EmployeeService {
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceIml(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee = null;
		
		if(result.isPresent()) {
			theEmployee= result.get();
		}
		else {
			//we didn't find the employee
			throw new RuntimeException("Didn't find employee id of- "+ theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
