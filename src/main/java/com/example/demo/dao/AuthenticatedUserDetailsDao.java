package com.example.demo.dao;

import com.example.demo.model.User;


/**
 * Retrieves the user details and roles 
 * 
 * @author 
 *
 */
public interface AuthenticatedUserDetailsDao {

	/**
	 * Loads user and role details with bcypt password details
	 * 
	 * @param userName
	 * @return
	 */
	public User loadUserDetails(String userName);
}
