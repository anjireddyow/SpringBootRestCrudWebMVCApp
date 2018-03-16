package com.example.demo.model;

import java.util.Set;

public class User {

	private int userId;
	
	private String userName;
	
	private String password;
	
	private String activeStatus;

	private Set<Role> rolesSet;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Set<Role> getRolesSet() {
		return rolesSet;
	}

	public void setRolesSet(Set<Role> rolesSet) {
		this.rolesSet = rolesSet;
	}
	
}
