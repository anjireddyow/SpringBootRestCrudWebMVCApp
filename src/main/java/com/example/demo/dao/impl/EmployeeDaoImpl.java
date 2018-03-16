package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;

/**
 * 
 * @author 
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	private static final Map<String, Employee> employeeMap = new HashMap<String, Employee>();
	
	static {
		initEmployee();
	}
	
	private static void initEmployee() {
		Employee emp1 = new Employee("01","John","Analyst");
		Employee emp2 = new Employee("02","Anna","Architect");
		Employee emp3 = new Employee("03","Peter","Manager");
		
		employeeMap.put(emp1.getEmpNo(), emp1);
		employeeMap.put(emp2.getEmpNo(), emp2);
		employeeMap.put(emp3.getEmpNo(), emp3);
	}
	
	public Employee getEmployee(String empNo) {
		return employeeMap.get(empNo);
	}
	
	public List<Employee> getEmployeeList() {
		List<Employee> employeesList = null;
		employeesList = new ArrayList<Employee>(employeeMap.values());
		return employeesList;
	}
	
	public Map<String, Employee> addEmployee(Employee addEmployee) {
		employeeMap.put(addEmployee.getEmpNo(), addEmployee);
		return employeeMap;
	}
	
	public Map<String, Employee> updateEmployee(Employee updateEmployee) {
		employeeMap.put(updateEmployee.getEmpNo(), updateEmployee);
		return employeeMap;
		
	}
	
	public Map<String, Employee> deleteEmployee(String empNo) {
		employeeMap.remove(empNo);
		return employeeMap;
	}
	
	
	
	
}
