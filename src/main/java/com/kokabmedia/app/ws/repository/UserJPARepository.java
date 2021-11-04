package com.kokabmedia.app.ws.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kokabmedia.app.ws.ui.model.response.UserRest;

/*
 * This interface is used for handling data to and from the database.
 * 
 * The @Repository annotation allows the Spring framework to creates an instance (bean) 
 * of this interface and manage it with the Spring Application Context (the IOC container)
 * that maintains all the beans for the application.  
 *
 * The @Repository annotation lets the Spring framework manage the UserJPARepository 
 * class as a Spring bean. The Spring framework will find the bean with auto-detection 
 * when scanning the class path with component scanning. It turns the class into a 
 * Spring bean at the auto-scan time.
 * 
 * @Repository annotation allows the UserRepository interface and its implementation to 
 * be wired in as dependency to a another object or a bean with the @Autowired annotation.
 * 
 * The @Repository annotation is a specialisation of @Component annotation for more specific 
 * use cases. 
 */
@Repository
/*
* Interface that gives access to to CRUD methods for handling data in a database,
* the JpaRepository interface has methods that perform SQL queries and lets the 
* application create and update data in the database, it takes an entity class 
* and the primary key type of that entity as argument.
* 
* JpaRepository is an abstraction over EntityManager
*/
public interface UserJPARepository extends JpaRepository<UserRest, Long>{
	
	

}
