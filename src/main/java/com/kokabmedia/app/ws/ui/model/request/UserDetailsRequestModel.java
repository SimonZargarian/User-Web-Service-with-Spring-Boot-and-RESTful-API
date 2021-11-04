package com.kokabmedia.app.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.GetMapping;

/*
 * This class will handle and model data coming in from a post HTTP request.
 */
public class UserDetailsRequestModel {

	/*
	 * These class fields have the same name structure as the JSON payload because
	 * they need to be mapped with the getters and setter methods so they can hold 
	 * the data that the JSON document contains, this will happen through the Spring
	 * framework with the @PostMapping annotation in the UserController class.
	 */
	@NotNull(message="First name connot be null") 
	@Size(min=2, message="First name must not be less than 2 characters")
	private String firstName;
	
	@NotNull(message="Last name connot be null")
	@Size(min=2, message="Last name must not be less than 2 characters")
	private String lastName;
	
	
	@NotNull(message="Email connot be null")
	@Email // Checks if this is a valid email address
	private String email;
	
	@NotNull(message="Password name connot be null")
	@Size(min=8,max=16, message="Password must be equal or greater than 8 characters and less than 16 characters")
	private String password;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * The purpose of this method is to returns a textual representation 
	 * of the object, instead of for example hash code in the logger.
	 */
	@Override
	public String toString() {
		return "UserDetailsRequestModel [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	

}
