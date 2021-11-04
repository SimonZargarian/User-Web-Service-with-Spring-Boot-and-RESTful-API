package com.kokabmedia.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.kokabmedia.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.kokabmedia.app.ws.ui.model.request.UserDetailsRequestModel;
import com.kokabmedia.app.ws.ui.model.response.UserRest;
import com.kokabmedia.app.ws.userservice.UserService;
import com.kokabmedia.app.ws.userservice.impl.UserServiceImpl;

/*
 * This class can also be named UserResource and its function is to handle HTTP requests, 
 * responses and expose recourses to other applications, functioning as a servlet.
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
 * is declared on the @RequestMapping annotation, in this case the appended "/users",
 * this class will called and response if needed. 
 * 
 * When a GET, POST, PUT or DELETER HTTP request is sent to the URL path with
 * the extension "/users" then the appropriate method in the class will respond.
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
@RequestMapping("/users") 
public class UserController {

	// Map for temporary storage
	Map<String, UserRest> usersMap; 

	/*
	 * The @Autowired annotation tells the Spring framework that the userService bean 
	 * implementation is an dependency of UserController class. It is a mechanism 
	 * for implementing Spring dependency injection.
	 * 
	 * @Autowired annotation enables dependency injection with Spring framework to avoid 
	 * tight coupling and enable loose coupling by calling the implementation of an interface.
	 * 
	 * The Spring framework creates a instance (bean) of the UserService implementation which 
	 * is the UserServiceImpl class and inject that instance into the UserController object when 
	 * it is instantiated as a autowired dependency.
	 * 
	 * The UserService implementation is now a dependency of the UserController class.
	 */
	@Autowired
	UserService userService;
	
	/*
	 * This method returns an user with a specific id.
	 * 
	 * The @GetMapping annotation will bind and make getUser method respond to a HTTP 
	 * GET request.
	 * 
	 * The (path = "/{userId}") parameter allows the method to read the appended
	 * String after the URL http://localhost:8080/users/ as a path variable that is
	 * attached, so when a string is appended after http://localhost:8080/users/
	 * with a GET HTTP request the getUser method is called. The name of the
	 * "/{userId}" parameter must match the @PathVariable annotation argument
	 * String userId.
	 * 
	 * The produces parameter enables the web service end point to return
	 * information in XML and JSON format.
	 */
	@GetMapping(path = "/{userId}", 
			produces = { 
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE })
	/*
	 * The @PathVariable annotation will make the path variable in the URL available
	 * for this getUser method via the method argument. When a user id string is
	 * appended to http://localhost:8080/users/ it can be handled by the getUser
	 * method.
	 * 
	 * The ResponseEntity object allows us to choose HTTP status code.
	 */
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		/*
		 * Collects RestUser with userId from URL path variable.
		 * 
		 * Creates new UserRest object in temporary storage map with userId that gets
		 * returned as ResponseEntity and a UserRest object.
		 * 
		 *  RespondEntity returns a HTTP code status ok with a UserRest object else
		 *  it returns status code no content.
		 */
		if(usersMap.containsKey(userId)) {
			return new ResponseEntity<>(usersMap.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	} 

	/*
	 * This method will be a web service endpoint that converts JSON paylod into a 
	 * java object.
	 * 
	 * The @PostMapping annotation will bind and make createUser method respond to
	 * a HTTP POST request. The HTTP POST request body will be passed to the @RequestBody
	 * in this case UserDetailsRequestModel UserDetails.
	 * 
	 * The consumes parameter enables the web service end point to consume
	 * information in XML and JSON format.
	 * 
	 * The produces parameter enables the web service end point to return
	 * information in XML and JSON format.
	 * 
	 * The @Valid annotation enables validation inside the UserDetailsRequestModel
	 * bean.
	 * 
	 * To be able to read a JSON body, the JSON payload need to be converted into a
	 * java object so that we can use it in this method, for that to happen we use
	 * the UserDetailsRequestModel class to hold the data that the JSON document
	 * contains.
	 * 
	 * The createUser method returns a ResponseEntity and a  UserRest object.
	 */
	@PostMapping(
			consumes = { 
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE }, 
			produces = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE })
	/*
	 * The @Valid annotation enables validation on UserDetailsRequestModel class.
	 * 
	 * The @RequestBody annotation enables this method to read information from a
	 * HTTP request that is coming in, when the JSON payload is read from the post
	 * request body it will be converted to a java object by the HTTP message converter 
	 * (Jackson) and mapped to UserDetailsRequestModel bean and the 
	 * createUser(@Valid @RequestBody UserDetailsRequestModel createUser() method can use the 
	 * userDetails object. 
	 * 
	 * The JSON payload will be converted into a java object.
	 */
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

		/*
		 * When we use the @Autowired annotation on the UserService interface the 
		 * UserServiceImp class is autowired in. If we autowired to the UserServiceImpl 
		 * class which is a Spring bean with @Service then the code would have to be: 
		 * UserRest returnValue = new UserServiceImpl().createUser(userDetails); it 
		 * would be a direct dependency and result in tight coupling. 
		 *  
		 * In this case the createUser(UserDetailsRequestModel UserDetails) method of 
		 * the UserService implementation (UserServiceImpl class) is used with the
		 * autowired userService object, which results in loose coupling making the 
		 * UserServiceImpl class independent from the UserContorller class.
		 * 
		 * When testing we can now mock the userService and test the
		 * createUser(@Valid @RequestBody UserDetailsRequestModel UserDetails) method
		 * of the UserController class independently, because we avoid direct dependency
		 * by not using the new keyword to create a new UserServiceImpl object, instead
		 * we autowire it's interface UserService and by doing so the UserServiceImpl 
		 * and the UserController do not have a direct dependency with each other and are 
		 * loosely coupled with the autowired UserService interface.
		 * 
		 * Now the The Spring framework will injects a UserServiceImpl instance (bean) 
		 * in to the UserController object when it is instantiated and the createUser 
		 * method of the UserServiceImpl class is called.
		 */
		UserRest returnValue = userService.createUser(userDetails);

		
		// Commented code section is to test the getUser method with Map
		/* 
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId =  UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
		if(usersMap == null) usersMap = new HashMap<>();
		usersMap.put(userId, returnValue);
		 */
		
		// RespondEntity returns a HTTP code status ok with a UserRest object
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		
	}

	/*
	 * The @PutMapping annotation will bind and make updateUser method respond to a 
	 * HTTP PUT request to specific user.
	 * 
	 * The (path = "/{userId}") parameter allows the method to read the appended
	 * String after the URL http://localhost:8080/users/ as a path variable that is
	 * attached, so when a string is appended after http://localhost:8080/users/
	 * with a PUT HTTP request the updateUser method is called. The name of the
	 * "/{userId}" parameter must match the @PathVariable annotation argument
	 * String userId.
	 * 
	 */
	@PutMapping(path = "/{userId}", 
			consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, 
			produces = { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String userId,
			@Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetails) {
		
		// Retrieve the correct UserRest object with the provided user id.
		UserRest userDetails = usersMap.get(userId);
		
		/* 
		 * Update the data from the body of PUT HTTP request with the
		 * updateUserDetails object.
		 */
		userDetails.setFirstName(updateUserDetails.getFirstName());
		userDetails.setLastName(updateUserDetails.getLastName());

		// Update the storage unit map.
		usersMap.put(userId, userDetails);

		return userDetails;
	}

	/*
	 * The @DeleteMapping annotation will bind and make deleteUser method respond
	 * to a HTTP DELETE request
	 * 
	 * The (path = "/{userId}") parameter allows the method to read the appended
	 * String after the URL http://localhost:8080/users/ as a path variable that is
	 * attached, so when a string is appended after http://localhost:8080/users/
	 * with a DELETE HTTP request the deleteUser method is called. The name of the
	 * "/{userId}" parameter must match the @PathVariable annotation argument
	 * String userId.
	 */
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {

		usersMap.remove(userId);
		return ResponseEntity.noContent().build();
	}

	/*
	 * This method will return a collection of users with pagination from a query
	 * string HTTP request parameter and assign it to a value, in this case the name
	 * of the @RequestParam annotation parameter "page" gets assign to the method 
	 * argument int page.
	 * 
	 * We have two request parameters available in this method as both request
	 * parameters are required by default, but the page parameter becomes optional
	 * with the defaultValue parameter.
	 * 
	 * The @GetMapping annotation will bind and make the getUser() method respond to a 
	 * HTTP GET request.
	 */
	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit) // http://localhost:8080/users?page=1&limit=50
	{
		return "get users was called with page = " + page + " and limit = " + limit;
		
	}

}
