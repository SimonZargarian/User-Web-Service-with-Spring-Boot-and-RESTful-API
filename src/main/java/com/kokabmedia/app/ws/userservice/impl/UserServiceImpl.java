package com.kokabmedia.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kokabmedia.app.ws.shared.Utils;
import com.kokabmedia.app.ws.ui.model.request.UserDetailsRequestModel;
import com.kokabmedia.app.ws.ui.model.response.UserRest;
import com.kokabmedia.app.ws.userservice.UserService;


/*
 * This class has the purpose of creating a User and implementing loose coupling by 
 * being an autowired implementation of the UserService interface, we do so that the 
 * UserServiceImpl class and the UserController class will be independent from each 
 * other when testing. 
 * 
 * When the UserService interface is autowired with the @Autowired annotation in the 
 * UserController class an new instance (bean) of UserServiceImpl i created by the
 * Spring framework. We can now use the createUser(UserDetailsRequestModel UserDetail)
 * method in UserServiceImpl class through a UserService object, avoiding tight coupling. 
 * 
 * The @Service annotation allows the Spring framework to creates an instance (bean) 
 * of this class an manage it with the Spring Application Context (the IOC container) that 
 * maintains all the beans for the application.  
 *
 * The @Service annotation lets the Spring framework manage the UserServiceImpl 
 * class as a Spring bean. The Spring framework will find the bean with auto-detection 
 * when scanning the class path with component scanning. It turns the class into a 
 * Spring bean at the auto-scan time.
 * 
 * @Service annotation allows the UserService interface and its implementation to be wired 
 * in as dependency to a another object or a bean with the @Autowired annotation.
 * 
 * The @Service annotation is a specialisation of @Component annotation for more specific 
 * use cases.  
 */
@Service
public class UserServiceImpl implements UserService {

	public Map<String, UserRest> usersMap;
	
	Utils utils;
	
	public UserServiceImpl(){}
	
	/*
	 * This is an example of constructor based dependency injection.
	 *  
	 * With the @Autowired annotation on the constructor the Spring framework 
	 * will automatically instantiate instances of objects that are passed as
	 * method arguments into the constructor. 
	 * 
	 * The Spring framework will automatically create instances of Utils class 
	 * when needed and autowire it to the autiwired UserService implementation
	 * when it is automatically initialised, we do not need to initialise a new
	 * UserServiceImp object with a Utils class (bean) as a argument in the 
	 * constructor.
	 */
	@Autowired 
	 UserServiceImpl(Utils utils)
	{
		this.utils = utils;
	}
	
	/*
	 * Method that creates new UserRest object from JSON payload and and stores it in a map
	 * for temporary storage. 
	 */
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		long userId = utils.generateUserId(); //Generated user id
		returnValue.setUserId(userId);
		
		if(usersMap == null) usersMap = new HashMap<>();
		
		return returnValue;
	}

}
