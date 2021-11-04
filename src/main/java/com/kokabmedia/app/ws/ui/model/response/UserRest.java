package com.kokabmedia.app.ws.ui.model.response;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

/*
 * This is a model class for the purpose of retrieving, creating, updating, deleting 
 * data with REST resources as well as with the database, mapping HTTP POST request 
 * body data to java objects with controller methods in the UserController and the 
 * UserJPAController class.  
 * 
 * The @Entity annotation from javax.persistence enables the JPA framework to manage 
 * the UserRest class as a JPA entity. The UserRest class is an entity and will be  mapped 
 * to a database table with the User_Rest. 
 * 
 * The @Entity annotation will automatically with Hibernate, JPA and Spring auto 
 * configuration create a User_Rest table in the database with the following SQL 
 * statement: cuser_rest (user_id bigint not null, email varchar(255) not null, first_name 
 * varchar(255) not null, last_name varchar(255).
 * 
 * This class acts as an mapped entity class for handling data in a database with the 
 * UserJPARepository class that extends JpaRepository.
 */
@Entity
public class UserRest {

	/*
	 * The @Id annotation makes this field a primary key in the database table.
	 * 
	 * The @GeneratedValue annotation makes the Hibernate generate the primary 
	 * key value.
	 * 
	 * Primary key will uniquely identify each row in a database table.
	 */
	@Id
	@GeneratedValue
	private long userId;
	
	@NotNull(message="First name connot be null") 
	@Size(min=2, message="First name must not be less than 2 characters")
	private String firstName;
	
	//@NotNull(message="Last name connot be null")
	@Size(min=2, message="Last name must not be less than 2 characters")
	private String lastName;
	
	@NotNull(message="Email connot be null")
	@Email // Checks if this is a valid email address
	private String email;
	
	@NotNull(message="Password name connot be null")
	@Size(min=8,max=16, message="Password must be equal or greater than 8 characters and less than 16 characters")
	private String password;

	/*
	 * JPA mandates a default no argument constructor, this constructor will be
	 * used by JPA to create this specific bean.
	 */
	public UserRest() {
	}

	public UserRest(long userId, String firstName, String lastName, String email, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/*
	 * The names of the fields and getter and setter methods must match the names of
	 * the data that is passed in the body of the the HTTP POST request, or else
	 * null will be returned.
	 */
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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
	 * The purpose of this method is to returns a textual representation of the
	 * object, instead of for example hash code in the logger.
	 */
	@Override
	public String toString() {
		return "UserRest [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

	
	

}
