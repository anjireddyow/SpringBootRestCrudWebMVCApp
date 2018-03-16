package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.common.CommonConstants;
import com.example.demo.dao.impl.AuthenticatedUserDetailsDaoImpl;
import com.example.demo.model.CustomSecurityUserDetails;
import com.example.demo.model.User;

/**
 * This is a spring security service class to retreive the user details and
 * roles from database
 * 
 * @author
 *
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(CustomerUserDetailsService.class);

	@Autowired
	private AuthenticatedUserDetailsDaoImpl authenticatedUserDetailsDaoImpl;

	@Override
	public CustomSecurityUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info(CommonConstants.APP_NAME_FOR_LOG + "user Name:" + userName);
		User user = authenticatedUserDetailsDaoImpl.loadUserDetails(userName);
		logger.info(CommonConstants.APP_NAME_FOR_LOG + user.getRolesSet().size());
//		if (user == null) {
//			throw new UsernameNotFoundException("User Name not found");
//		}
		return new CustomSecurityUserDetails(user);
	}

}
