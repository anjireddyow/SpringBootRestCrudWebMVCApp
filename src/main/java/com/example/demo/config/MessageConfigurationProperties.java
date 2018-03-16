package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Messages properties
 * 
 * @author 
 *
 */
@Component
@ConfigurationProperties("messages")
public class MessageConfigurationProperties {

	private String addemployeesuccess;
	
	private String addemployeefailure;

	public String getAddemployeesuccess() {
		return addemployeesuccess;
	}

	public void setAddemployeesuccess(String addemployeesuccess) {
		this.addemployeesuccess = addemployeesuccess;
	}

	public String getAddemployeefailure() {
		return addemployeefailure;
	}

	public void setAddemployeefailure(String addemployeefailure) {
		this.addemployeefailure = addemployeefailure;
	}
	
	
}
