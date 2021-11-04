package com.kokabmedia.app.ws.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * This class will handle and model data coming in from a PUT HTTP request when 
 * updating a User.
 */
public class UpdateUserDetailsRequestModel {

	 /* 
	 * These class fields have the same name structure as the JSON payload because
	 * they need to be mapped with the getters and setter methods so they can hold 
	 * the data that the JSON document contains, this will happen through the Spring
	 * framework with the @PutMapping annotation in the UserController class.
	 */
	@NotNull(message = "First name connot be null")
	@Size(min = 2, message = "First name must not be less than 2 characters")
	private String firstName;

	@NotNull(message = "Last name connot be null")
	@Size(min = 2, message = "Last name must not be less than 2 characters")
	private String lastName;

	/*
	 * The names of the fields and getter and setter methods must match the names of the
	 * data that is passed in the body of the the HTTP POST request, or else null will be 
	 * returned.
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * The purpose of this method is to returns a textual representation 
	 * of the object, instead of for example hash code in the logger.
	 */
	@Override
	public String toString() {
		return "UpdateUserDetailsRequestModel [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
