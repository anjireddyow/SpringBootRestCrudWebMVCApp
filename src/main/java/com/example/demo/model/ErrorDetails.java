package com.example.demo.model;

import java.util.Date;
/**
 * This class is used for generating customer error message details for custom exception handling
 * 
 * @author 
 *
 */
public class ErrorDetails {

	private Date errorDate;
	private String errorMessage;
	private String errorDetails;
	
	public ErrorDetails(Date errorDate, String errorMessage, String errorDetails) {
		this.errorDate = errorDate;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
	}

	public Date getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
}
