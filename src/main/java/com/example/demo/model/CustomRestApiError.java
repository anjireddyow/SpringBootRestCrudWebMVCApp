package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Custom Rest API Error Details Class for more information capturing for error
 * 
 * @author
 *
 */
public class CustomRestApiError {

	private HttpStatus httpStatus;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyy hh:mm:ss")
	private LocalDateTime dateTimeStamp;

	private String message;

	private String debugMessage;

	private String details;

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	private List<CustomRestApiSubError> subErrors;

	private CustomRestApiError() {
		dateTimeStamp = LocalDateTime.now();
	}

	CustomRestApiError(LocalDateTime dateTimeStamp, String message, String details) {
		super();
		this.dateTimeStamp = dateTimeStamp.now();
		this.message = message;
		this.details = details;		
	}

	CustomRestApiError(HttpStatus httpStatus) {
		this();
		this.httpStatus = httpStatus;
	}

	CustomRestApiError(HttpStatus httpStatus, Throwable ex) {
		this();
		this.httpStatus = httpStatus;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	CustomRestApiError(HttpStatus httpStatus, String message, Throwable ex) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getDateTimeStamp() {
		return dateTimeStamp;
	}

	public void setDateTimeStamp(LocalDateTime dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

}

abstract class CustomRestApiSubError {

}

class ApiValidationError extends CustomRestApiSubError {
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}
}
