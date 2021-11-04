package com.kokabmedia.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kokabmedia.app.ws.repository.UserJPARepository;
import com.kokabmedia.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.kokabmedia.app.ws.ui.model.request.UserDetailsRequestModel;
import com.kokabmedia.app.ws.ui.model.response.UserRest;
import com.kokabmedia.app.ws.userservice.UserService;
import com.kokabmedia.app.ws.userservice.impl.UserServiceImpl;

/*
 * This class can also be named UserJPAResource and its function is to handle HTTP requests, 
 * responses and expose recourses to other applications, functioning as a servlet and a as 
 * class that communicates with the repository class and the embed database retrieving,  
 * deleting, updating and sending data, this class will also convert a JSON payload to a 
 * java object of the entity class that is mapped to a database table.
 * 
 * The @RestController annotation will register this class as a Rest Controller and it will
 * be able to receive HTTP request when they are sent and match the URL path.
 * 
 * With this annotation the class can now handle REST requests.
 * 
 * @Response body annotation which is part of the @RestController annotation is responsible 
 * for sending information back from the application to another application. 
 * 
 * When we put @ResponseBody on a controller, the response from that will be mapped by a 
 * http message converter(Jackson) into another format, for example a java object to JSON, 
 * XML or HTML. Response body converts the java object and sends the response back. 
 */
@RestController
/*
 * When HTTP request is sent to a certain URL and that URL contains a path which
 * is declared on the @RequestMapping annotation, in this case the appended "jpa/users",
 * this class will called and response if needed. 
 * 
 * When a GET, POST, PUT or DELETER HTTP request is sent to the URL path with
 * the extension "jpa/users" then the appropriate method in the class will respond.
 * 
 * This is a request mapping for the entire class. 
 * 
 * The dispatcher servlet is the Front Controller for the Spring MVC framework 
 * handles all the requests of the root (/) of the web application. 
 * 
 * Dispatcher servlet knows all the HTTP request methods GET, POST, PUT AND DELETE 
 * and what java methods they are mapped to with annotations. Dispatcher servlet will 
 * delegate which controller should handle a specific request. Dispatcher servlet looks 
 * at the URL and the request method.  
 */
@RequestMapping("jpa/users")
public class UserJPAController {

	/*
	 * The @Autowired annotation tells the Spring framework that the UserJPARepository bean 
	 * and its implementation is an dependency of UserController class. It is a mechanism 
	 * for implementing Spring dependency injection.
	 * 
	 * @Autowired annotation enables dependency injection with Spring framework to avoid 
	 * tight coupling and enable loose coupling by calling a interface or the implementation 
	 * of an interface.
	 * 
	 * The Spring framework creates a instance (bean) of the UserJPAController or its implementation 
	 * and inject (autowires) that instance into the UserJPAResource object when it is instantiated 
	 * as a autowired dependency.
	 * 
	 * The UserJPARepository and its implementation is now a dependency of the UserJPAController class.
	 */
	@Autowired
	private UserJPARepository userJpaRepository;
	
	/*
	 * This method will return a collection of users from the database using JPA.
	 * 
	 * When HTTP request is sent to a certain URL and that URL contains a path which
	 * is declared on the @GetMapping annotation, in this case the appended "jpa/users" this method 
	 * will be called.
	 * 
	 * The @GetMapping annotation will bind and make getUsers method respond to a HTTP GET request.
	 */
	@GetMapping
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit)// http://localhost:8080/jpa/users?page=1&limit=50
	{
		/*
		 * This userRepository bean is managed by the Spring framework with
		 * dependency injection with autowiring.
		 * 
		 * findAll() method retrieves all users from the database.
		 */
		return userJpaRepository.findAll();
	}
	
	/*
	 * This method returns an user with a specific id from the database using JPA.
	 * 
	 * The @GetMapping annotation will bind and make getUser method respond to a HTTP 
	 * GET request.
	 * 
	 * The (path = "/{userId}") parameter allows the method to read the appended
	 * String after the URL http://localhost:8080/users/ as a path variable that is
	 * attached, so when a string is appended after http://localhost:8080/users/
	 * with a GET HTTP request the getUser method is called. The name of the
	 * "/{userId}" parameter must match the @PathVariable annotation argument
	 * long userId.
	 * 
	 * The produces parameter enables the web service end point to return
	 * information in XML and JSON format.
	 */
	@GetMapping(path = "/{userId}", // http://localhost:8080/jpa/users/userId
			produces = { 
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE })
	
	/*
	 * The @PathVariable annotation will make the path variable in the URL available
	 * for this getUser method via the method argument. When a user id string is
	 * appended to http://localhost:8080/users/ it can be handled by the getUser()
	 * method.
	 * 
	 * The ResponseEntity object allows us to choose HTTP status code.
	 */
	public ResponseEntity<UserRest> getUser(@PathVariable long userId) {
		
		/*
		 * Retrieves a a specific user from the H2 in memory database with 
		 * the @PathVariable annotation parameter userId.
		 * 
		 * Optional ensures that even if User is null a proper object will be returned.
		 */
		Optional<UserRest> user = userJpaRepository.findById(userId);
		
		/*
		 *  RespondEntity returns a HTTP code status ok with a UserRest object else
		 *  it returns status code no content.
		 */
		if(user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	} 

	/*
	 * This method creates a new user with HTTP POST request containing a JSON body and stores
	 * it in the database using JPA. We get the content of the user coming is as 
	 * part of the request body.
	 * 
	 * The createUser() method will be a web service endpoint that converts JSON
	 * paylod into a java object.
	 * 
	 * The @PostMapping annotation will bind and make createUser method respond to
	 * a HTTP POST request. The HTTP POST request body will be passed to the @RequestBody
	 * in this case UserRest user.
	 * 
	 * The consumes parameter enables the web service end point to consume
	 * information in XML and JSON format.
	 * 
	 * The produces parameter enables the web service end point to return
	 * information in XML and JSON format.
	 * 
	 * The @Valid annotation enables validation inside the UserRest bean.
	 * 
	 * To be able to read a JSON body, the JSON payload need to be converted into a
	 * java object so that we can use it in this method, for that to happen we use
	 * the UserRest class to hold the data that the JSON document contains.
	 * 
	 * The createUser method returns a ResponseEntity and a UserRest object.
	 */
	@PostMapping(
			consumes = { 
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE }, 
			produces = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE })
	
	/*
	 * The @Valid annotation enables validation on UserRest class.
	 * 
	 * The @RequestBody annotation enables this method to read information from a
	 * HTTP request that is coming in, when the JSON payload is read from the post
	 * request body it will be converted a java object by the HTTP message converter (Jackson) 
	 * and mapped to UserRest bean and the 
	 * createUser(@Valid @RequestBody UserRest user) method can use the userDetails object. 
	 * 
	 * The JSON payload will be converted into a java object.
	 */
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRest user) { 

		
		// Save a user in the H2 in memory database.
		UserRest returnValue = userJpaRepository.save(user); 		
		
		// RespondEntity returns a HTTP code status ok with a UserRest object
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	/*
	 * This method updates a specific user with HTTP PUT request containing a JSON body and 
	 * stores it in the database using JPA. We get the content of the user 
	 * coming is as part of the request body.
	 * 
	 * The @PutMapping annotation will bind and make updateUser() method respond to a 
	 * HTTP PUT request to specific user.
	 * 
	 * The (path = "/{userId}") parameter allows the method to read the appended
	 * String after the URL http://localhost:8080/users/ as a path variable that is
	 * attached, so when a string is appended after http://localhost:8080/users/
	 * with a PUT HTTP request the updateUser method is called. The name of the
	 * "/{userId}" parameter must match the @PathVariable annotation argument
	 * long userId.
	 */
	@PutMapping(path = "/{userId}", 
			consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, 
			produces = { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable long userId,
			@Valid @RequestBody UpdateUserDetailsRequestModel userDetails) { 
		
		/* 
		 * Retrieve the correct UserRest object with the provided user id.
		 * from the h2 database.
		 */
		//UserRest updatedUser = userJpaRepository.getById(userId);
		UserRest updatedUser = userJpaRepository.findById(userId).get();

		
		/* 
		 * Update the data from the body of PUT HTTP request with the
		 * updateUserDetails object.
		 */
		updatedUser.setFirstName(userDetails.getFirstName());
		updatedUser.setLastName(userDetails.getLastName());

		/*
		 * Save the updated user in the H2 database with the new
		 * incoming values.
		 */
		userJpaRepository.save(updatedUser);
		// Update the storage unit map.
		//usersMap.put(userId, userDetails);
		return updatedUser;
		
		/*
		 *  RespondEntity returns a HTTP code status ok with a UserRest object else
		 *  it returns status code no content.
		 */
	}

	/*
	 * This method will delete a user with a specific id from the H2 in memory database using jPA.
	 * 
	 * When a HTTP DELETE request is sent to a certain URL and that URL contains a path which
	 * is declared on the @DeleteMapping annotation, in this case the appended "/jpa/users", this 
	 * method will be called.
	 * 
	 * The (path = "/{userId}") parameter allows the method to read the appended
	 * String after the URL http://localhost:8080/jpa/users/ as a path variable that is
	 * attached, so when a string is appended after http://localhost:8080/jpa/users/
	 * with a DELETE HTTP request the deleteUser method is called. The name of the
	 * "/{userId}" parameter must match the @PathVariable annotation argument
	 * long userId.
	 * 
	 * The @DeleteMapping annotation will bind and make deleteUser() respond to a HTTP DELETE
	 * request.
	 */
	@DeleteMapping(path = "/{userId}")
	public void deleteUser(@PathVariable long userId) {

		userJpaRepository.deleteById(userId);
	}

}
