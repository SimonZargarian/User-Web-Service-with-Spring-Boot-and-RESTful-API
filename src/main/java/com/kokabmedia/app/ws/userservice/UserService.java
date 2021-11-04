package com.kokabmedia.app.ws.userservice;

import java.util.Map;

import com.kokabmedia.app.ws.ui.model.request.UserDetailsRequestModel;
import com.kokabmedia.app.ws.ui.model.response.UserRest;
/*
 * Interface for service layer object.
 * 
 * The practise of coding against an interface implements loose coupling with
 * the @Autowired annotation allowing dependency injection and better unit testing.
 */
public interface UserService {
	
	UserRest createUser(UserDetailsRequestModel UserDetails);
	
	

}
