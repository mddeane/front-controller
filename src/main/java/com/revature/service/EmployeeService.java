package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class EmployeeService {

	// the service class relies on (depends on) the dao
	
	private EmployeeDao edao;
	
	// constructor injection
	
	/**
	 * Dependency Injection by way of Constructor Injection
	 * 
	 * Constructor Injection is a sophisticated way of ensuring no EmployeeService object
	 * is instantiated without passing through an EmployeeDao object
	 * 
	 * 
	 */
	
	public EmployeeService(EmployeeDao edao) {
		this.edao = edao;
	}
	
	// username and password will be taken from HTML form
	/**
	 * This method will return an Employee that has successfully logged in
	 * meaning that their username and password match a unique record in the DB.
	 * 
	 * Return null if it doesn't match.
	 * 
	 * @param username - comes from a parameter from an HTML form
	 * @param password - same with password
	 * @return Employee object complete with the Employee's first and last name
	 */
	
	public Employee confirmLogin(String username, String password) {
	
		Optional<Employee> possibleEmp = edao.findAll()
				.stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();
		// if the employee object is present, return it, otherwise return null
		return (possibleEmp.isPresent() ? possibleEmp.get():null);
	}
	

	public List<Employee> findAll() {
		return edao.findAll();
	}
	
	public int insert(Employee e) {
		return edao.insert(e);
	}
	
}
