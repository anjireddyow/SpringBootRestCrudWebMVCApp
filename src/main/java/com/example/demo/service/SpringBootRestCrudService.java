package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SpringBootRestCrudService {

	@Autowired
	private EmployeeJDBCTemplateDao employeeJDBCTemplateDao;

	/**
	 * 
	 * @return
	 */
	public List<Employee> getEmployees() {
		return employeeJDBCTemplateDao.getEmployees();
	}
}
