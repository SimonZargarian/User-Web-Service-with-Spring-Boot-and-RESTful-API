package com.kokabmedia.app.ws.exeption;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kokabmedia.app.ws.ui.model.response.ErrorMessage;

/*
 * This class is for handling exceptions for the whole application.
 * 
 * The @ControllerAdvice annotation registers this class with the Spring framework
 * and make it able to listen to exceptions that take place in the application and 
 * listens across all mapping methods.
 */
@ControllerAdvice
public class AppExeptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * This method handles General Exceptions.
	 * 
	 * The @ExceptionHandler annotation enables the handleAnyExeption method to
	 * handle exception that occur in the application.
	 */
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyExeption(Exception ex, WebRequest request)
	{
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		
		return new ResponseEntity<>(
				ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * This method handles Null Pointer exceptions.
	 */
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerExeption(NullPointerException ex, WebRequest request)
	{
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		
		return new ResponseEntity<>(
				ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/*
	 * This method handles user service exception.
	 */
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request)
	{
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		
		return new ResponseEntity<>(
				ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
