package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeJDBCTemplateDao;
import com.example.demo.model.Employee;

/**
 * Spring Boot Rest Service for making all the DAO calls
 * 
 * @author
 *
 */
@Service
public class SpringBootRestCrudJDBCService {

	@Autowired
	private EmployeeJDBCTemplateDao employeeJDBCTemplateDao;

	/**
	 * 
	 * @return
	 */
	public List<Employee> getEmployees() {
		return employeeJDBCTemplateDao.getEmployees();
	}
	
	/**
	 * Add the employee details to the map
	 * 
	 * @param addEmployee
	 * @return
	 * @throws DataAccessException
	 */
	public String addEmployee(Employee addEmployee) throws DataAccessException{
		return employeeJDBCTemplateDao.addEmployee(addEmployee);
	}

	/**
	 * update the employee details in the map
	 * 
	 * @param updateEmployee
	 */
	public void updateEmployee(Employee updateEmployee) {
		employeeJDBCTemplateDao.updateEmployee(updateEmployee);
	}

	/**
	 * Delete the employee details in the map
	 * 
	 * @param employee_number
	 */
	public void deleteEmployee(int employee_number) {
		employeeJDBCTemplateDao.deleteEmployee(employee_number);
	}
}
