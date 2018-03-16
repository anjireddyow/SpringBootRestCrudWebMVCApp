package com.example.demo.exception.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Custom Access Denied Handler for displaying the access denied page
 * 
 * @author 
 *
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
			throws IOException, ServletException {
		// in our CustomAccessDeniedHandler, we can customize the response as we wish by redirecting or display a custom error message.
		response.sendRedirect("/accessDenied");
	}
}
