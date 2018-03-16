package com.example.demo.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.model.Employee;

/**
 * This will perform the CRUD operations for Employee details from the database
 * 
 */
public interface EmployeeJDBCTemplateDao {

	/**
	 * 
	 * @return the list of employees
	 */
	public List<Employee> getEmployees();

	/**
	 * Add the employee details to the map
	 * 
	 * @param addEmployee
	 * @return
	 * @throws DataAccessException
	 */
	public String addEmployee(Employee addEmployee) throws DataAccessException;

	/**
	 * update the employee details in the map
	 * 
	 * @param updateEmployee
	 */
	public void updateEmployee(Employee updateEmployee);

	/**
	 * Delete the employee details in the map
	 * 
	 * @param employee_number
	 */
	public void deleteEmployee(int employee_number);
}