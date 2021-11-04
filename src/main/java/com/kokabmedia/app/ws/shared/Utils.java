package com.kokabmedia.app.ws.shared;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

/*
 * This utility class is used to generate user id.
 * 
 * The @Service annotation allows the Spring framework to creates an instance (bean) of this 
 * class an manage it with the Spring Application Context (the IOC container) that maintains
 * all the beans for the application.  
 *
 * The @Service annotation lets the Spring framework manage the Utils class as a Spring bean.
 * The Spring framework will find the bean with auto-detection when scanning the class path 
 * with component scanning. It turns the class into a Spring bean at the auto-scan time.
 * 
 * @Service annotation allows the Utils class to be wired in as dependency to a another object or 
 * a bean with the @Autowired annotation.
 * 
 * The @Service annotation is a specialisation of @Component annotation for more specific 
 * use cases. 
 * 
 * An instance (bean) of this class will be created and injected in to the constructor 
 * of the UserServiceImpl class when UserServiceImpl class is instantiated.
 */
@Service
public class Utils {

	Random random = new Random();

	public long generateUserId() {
		return random.nextLong();
		}
}
