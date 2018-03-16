package com.example.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee>{

//	@Override
//	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmpNo(Integer.toString(rs.getInt("employee_number")));
		employee.setEmpName(rs.getString("employee_name"));
		employee.setEmpPosition(rs.getString("employee_position"));
		return employee;
	}

}
