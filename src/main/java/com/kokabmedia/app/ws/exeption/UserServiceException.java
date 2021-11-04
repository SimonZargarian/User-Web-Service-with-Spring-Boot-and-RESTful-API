package com.kokabmedia.app.ws.exeption;


/*
 * This class represents a custom exception handler that handles
 * Runtime Exception.
 */
public class UserServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -7988176124726195226L;

	public UserServiceException(String message) 
	{
		super(message);
	}

}
