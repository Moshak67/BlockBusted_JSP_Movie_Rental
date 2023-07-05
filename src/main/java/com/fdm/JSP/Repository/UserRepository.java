package com.fdm.JSP.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.JSP.model.User;
/** This class provides the mechanism to store retrieve update or remove objects from the database
 * 	this class acts as our database repository and runs commands on the database.
 * @author Mohammad Shakir
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/** custom method to find a user. may or may not contain a non-null value
	 * @param username to be proccessed by the command
	 * @param password to be proccessed by the command
	 * @return a Optional User object r with matching attributes from the database
	 */
	Optional<User> findByUsernameAndPassword(String username,String password);
	
	
	
	/** Custom method to find a user using their username. 
	 * @param username to be checked in the database
	 * @return a Optional User object 
	 */
	Optional<User> findByUsername(String username);
	


	
}
