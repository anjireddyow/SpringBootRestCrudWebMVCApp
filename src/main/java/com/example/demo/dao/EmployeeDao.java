package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.model.Employee;

/**
 * This will add CRUD operation of employee details to map object
 * 
 * @author
 *
 */
public interface EmployeeDao {

	/**
	 * 
	 * @param empNo
	 * @return the list of employees (hardcoded values in the code)
	 */
	public Employee getEmployee(String empNo);

	/**
	 * 
	 * @return
	 */
	public List<Employee> getEmployeeList();

	/**
	 * 
	 * @param addEmployee
	 * @return
	 */
	public Map<String, Employee> addEmployee(Employee addEmployee);

	/**
	 * 
	 * @param updateEmployee
	 * @return
	 */
	public Map<String, Employee> updateEmployee(Employee updateEmployee);

	/**
	 * 
	 * @param empNo
	 * @return
	 */
	public Map<String, Employee> deleteEmployee(String empNo);
}
