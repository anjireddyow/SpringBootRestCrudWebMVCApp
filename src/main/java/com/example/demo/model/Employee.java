package com.example.demo.model;

public class Employee {

	private String empNo;
	private String empName;
	private String empPosition;
	
	public Employee() {
		
	}
	
	public Employee(String empNo, String empName, String empPosition) {
		this.empNo = empNo;
		this.empName = empName;
		this.empPosition = empPosition;
	}
	
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}
	
	
}
