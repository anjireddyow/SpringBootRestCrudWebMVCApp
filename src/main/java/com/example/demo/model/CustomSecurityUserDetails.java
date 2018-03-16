package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomSecurityUserDetails implements UserDetails{

	@Autowired
	private User user;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomSecurityUserDetails(User user) {
		this.user = user;
	}
	
	/**
	 * Please assign "ROLE_" for each role in authoritiese. Since Spring security by default will include ROLE_ for each role
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities  = new ArrayList<>();
	    for (Role role: user.getRolesSet()) {
	    	System.out.println("Anji Role Name" + role.getRoleName());
	        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
	    }
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
