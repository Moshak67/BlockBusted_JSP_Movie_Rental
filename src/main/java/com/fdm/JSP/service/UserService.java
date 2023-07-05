package com.fdm.JSP.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fdm.JSP.Repository.UserRepository;
import com.fdm.JSP.model.User;

/**
 * This class implements the userRepository methods to save , update remove and
 * find users. This class is the main point of contact in the controller page.
 * the user can use this class to perform crud functionalities.
 * 
 * @author Mohammad Shakir
 *
 */
@Service
public class UserService {
	private UserRepository userRepo;

	private Log log = LogFactory.getLog(UserService.class);		

	/** Constructor for dependency injection
	 * @param userRepo an instance of the user repository is injected as an argument. 
	 * the methods from the repo can be used in this class now.
	 */
	@Autowired
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;

	}

	/**
	 * Helper method to check if a user exist in the database using their username
	 * this method is used to check for duplicate usernames
	 * 
	 * @param username that we want to check in the database
	 * @return true if found false otherwise
	 */
	public boolean usernameExist(String username) {
		if (userRepo.findByUsername(username).isPresent()) {
			return true;
		}
		return false;	
	}
	
	

	/**
	 * Method to add a user to the database
	 * 
	 * @param userToSave an instance of the user class
	 * @return calls on user repository internal save method to save the user data in user database
	 */
	public User save(User userToSave) {
		log.info("user was inserted into the database using save method in service");
		userRepo.save(userToSave);
		return userToSave;
	}

	/** Method to update user details , only the active user can update details
	 * 	if the user is not logged in they will be redirected to a log in page via the controller class
	 * @param activeUser this is the user we want to update, we access their data by finding their info by id
	 * @param returnedUser this is the returned user detail from the forms that we pass on to update the user info
	 * @return calls on user repo save method and passes the newly updated user to the database.
	 */
	public User update(User activeUser, User returnedUser) {
		Optional<User> toUpdate = userRepo.findById(activeUser.getId());
		if (toUpdate.isEmpty()) {
			return null;
		}	
		User newUser = toUpdate.get();
		newUser.setEmail(returnedUser.getEmail());
		newUser.setfName(returnedUser.getfName());
		newUser.setlName(returnedUser.getlName());
		newUser.setPassword(returnedUser.getPassword());
		newUser.setUsername(returnedUser.getUsername());

		log.info("update service " + newUser);
		userRepo.save(newUser);
		return newUser;
	}

	/** This method removes a user and their data from the data base.
	 * 	If there is an instance of the user available
	 * 	the user is retrieved from the data base by their id, 
	 * 	then this user is passed on to the delete by id method.
	 * 	
	 * 
	 * @param user instance that we want to delete
	 * @return null if there is no instance of user or the user is null
	 */
	public User remove(User user) {
		
		if (user != null) {
			log.info("Deleting this user: " + user);
			userRepo.deleteById(user.getId());
		}
		log.info("there is no user in session");
		return null;
	}
	
	public void removeById(int id) {
		log.info("was deleted" + userRepo.findById(id));
		userRepo.deleteById(id);
	}
	
	public List<User> listAll() {
		return userRepo.findAll();
	}

	public User get(int id) {
		return userRepo.findById(id).get();
	}
		

	/**
	 * Method to check username and password to make sure the user exist in the
	 * database
	 * 
	 * @param user instance that we want to check their username and password
	 * @return if user exist we return their details otherwise we return null
	 */
	public User checkLogin(User user) {
		String username = user.getUsername();	
		String password = user.getPassword();
		
		Optional<User> userExist = userRepo.findByUsernameAndPassword(username, password);
		if (userExist.isEmpty()) {
			log.info("Invalid Username or Password");
		}
		log.info("checkLogin method userExist " + userExist);
		
		if (userExist.isPresent()) {
			return userExist.get();
		}
		
		return null;
	
	}

}
