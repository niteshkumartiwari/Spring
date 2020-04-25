package com.nitesh.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springboot.cruddemo.dao.EmployeeDAO;
import com.nitesh.springboot.cruddemo.entity.Employee;
import com.nitesh.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	//expose "/employees/{employeeId}" to get single employee
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("Employee Id not found- "+employeeId);
		}
		
		return theEmployee;
	}
	
	//add Mapping for POST /employees -add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		//also in case they pass an ID in JSON....set id to 0
		//this is to force a save of new item...instead of update
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	//add Mapping for PUT /employees -update existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	//add Mapping for DELETE /employees/{employeeId}
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee=  employeeService.findById(employeeId);
		
		//throw exception of null
		if(tempEmployee == null) {
			throw new RuntimeException("Employee id doesn't exists- "+employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted employeeID of -"+employeeId;
	}
}
