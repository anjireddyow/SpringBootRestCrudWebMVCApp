package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.model.ErrorDetails;

/**
 * This is a Customer Exception handler in Spring boot
 * 
 * @author 
 *
 */
@ControllerAdvice
@RestController
public class RestCustomizedDefaultResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	/**
	 * Using Error Response Structure for EmployeeNotFoundException
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	  @ExceptionHandler(EmployeeNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	  
	/**
	 * Using Error Response Structure for all Exceptions
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	  @ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
