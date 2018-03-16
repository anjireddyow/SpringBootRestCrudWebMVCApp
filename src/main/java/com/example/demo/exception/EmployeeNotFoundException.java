package com.example.demo.exception;

/**
 * Customizing Return Status for a Specific Exception
 * 
 * Exception   HTTP Status Code 
 * --------------------------
 * BindException 400 (Bad Request)
 * ConversionNotSupportedException 500 (Internal Server Error)
 * HttpMediaTypeNotAcceptableException 406 (Not Acceptable)
 * HttpMediaTypeNotSupportedException 415 (Unsupported Media Type)
 * HttpMessageNotReadableException 400 (Bad Request)
 * HttpMessageNotWritableException 500 (Internal Server Error)
 * HttpRequestMethodNotSupportedException 405 (Method Not Allowed)
 * MethodArgumentNotValidException 400 (Bad Request)
 * MissingServletRequestParameterException 400 (Bad Request)
 * MissingServletRequestPartException 400 (Bad Request)
 * NoSuchRequestHandlingMethodException 404 (Not Found) TypeMismatchException
 * 400 (Bad Request)
 * 
 * @author
 *
 */
// @ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeNotFoundException(Throwable cause) {
		super(cause);
	}

	protected EmployeeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
