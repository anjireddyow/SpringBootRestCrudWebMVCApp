package com.example.demo.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.CustomApiError;

/**
 * 
 * Spring 3.2 brings support for a global @ExceptionHandler with the
 * new @ControllerAdvice annotation. This enables a mechanism that breaks away
 * from the older MVC model and makes use of ResponseEntity along with the type
 * safety and flexibility of @ExceptionHandler:
 * 
 * The new annotation allows the multiple scattered @ExceptionHandler from
 * before to be consolidated into a single, global error handling component.
 * 
 * The actual mechanism is extremely simple but also very flexible:
 * 
 * it allows full control over the body of the response as well as the status
 * code it allows mapping of several exceptions to the same method, to be
 * handled together it makes good use of the newer RESTful ResposeEntity
 * response
 * 
 * @author
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	/**
	 * Just for reference only (handle method level
	 * security @PreAuthorize, @PostAuthorize, and @Secure Access Denied.)
	 * 
	 * @param ex
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest webRequest) {
		return new ResponseEntity<>("Access Denied Error Custom Message", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
		// public final ResponseEntity<ErrorDetails>
		// handleUserNotFoundException(EmployeeNotFoundException ex,
		// WebRequest request) {
		// ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
		// request.getDescription(false));
		// return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		CustomApiError apiError = new CustomApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("The parameter '%s' of value '%s' could not be converted to type '%s'");
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(CustomApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
