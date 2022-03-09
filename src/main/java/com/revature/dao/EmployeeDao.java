package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {

	// insert th eemployee object by calling the save() method from Hibernate, return the PK generated
	public int insert(Employee e) {
	
		// grab the session object
		Session ses = HibernateUtil.getSession();
		
		// begin a tx
		Transaction tx = ses.beginTransaction(); // importr Transaction from Hibernate
		
		// capture the pk returned
		int pk = (int) ses.save(e);
		
		// commit the tx
		tx.commit();
		
		// return the pk
		return pk;
	}
	
	// findAll() method that returns a list of Employees
	public List<Employee> findAll() {
		// grab the session
		Session ses = HibernateUtil.getSession();
		
		// make an HQL statement
		List<Employee> emps = ses.createQuery("from Employee", Employee.class).list();
		
		// return list of Employees
		return emps;
	}
	
	public boolean delete(int id) {
		return false;
	}
	
	public boolean update(Employee e) {
		return false;
	}
}
