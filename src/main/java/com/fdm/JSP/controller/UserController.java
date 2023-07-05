package com.fdm.JSP.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdm.JSP.model.User;
import com.fdm.JSP.service.RentalService;
import com.fdm.JSP.service.UserService;

/**
 * User Controller class to allow user to perform CRUD functionality Register
 * login log out check active user details, update and remove user from database
 * performs these tasks via the UserService class which provides methods to
 * perform the tasks mentioned above.
 * 
 * @author Mohammad shakir
 *
 */
@Controller
public class UserController {
	final String REDIRECT_MOVIE_LIST = "redirect:/movieList";
	final String REDIRECT_LOGIN = "redirect:/login";
	 final String ERROR_UPDATE_MESSAGE = "errorUpdate";
	final String ACTIVE_USER = "user";
	final String USERNAME_ALREADY_EXIST = "/usernameAlreadyExist";

	final String GET_DELETE_MESSAGE = "/deleteMessage";
	final String GET_DELETE = "/delete";
	final String GET_UPDATE_SUCCESS = "/updateSuccess";
	final String GET_UPDATE = "/update";
	final String GREETING_JSP = "/greeting";
	final String GET_LOGOUT = "/getLogout";
	final String GET_CHECK_USER = "/checkUser";
	final String GET_LOGIN = "/login";
	final String PROCCESS_LOGIN = "/proccessLogin";
	final String PROCESS_REGISTER = "/processRegister";
	final String GET_REGISTER = "/register";
	final String GREETING_PAGE = "/greeting";

	private Log log = LogFactory.getLog(UserController.class);

	private UserService userService;
	
	private RentalService rentalService;

	/**
	 * constructor for dependency injection
	 * 
	 * @param userService an instance of user service is injected into the user
	 *                    controller as an argument
	 */
	@Autowired
	public UserController(UserService userService, RentalService rentalService) {
		super();
		this.userService = userService;
		this.rentalService = rentalService;
	}
// 	TODO user should not be able to submit form with empty fields, and the user detail should be unique
	/**
	 * handler method to get register page adds the user object to the model
	 * 
	 * @param model holder for model attributes
	 * @return register jsp page including a spring form to take user details
	 */
	@GetMapping(GET_REGISTER)
	public String getRegisterPage(Model model) {
		User userObject = new User();
		log.info("get register page userObject" + userObject);
		model.addAttribute(ACTIVE_USER, userObject);
		return GET_REGISTER;
	}

	/**
	 * Handler method to process the registration form using Post Method checks for
	 * empty fields and shows warning if user did not fill any fields. this is
	 * handle by the BindingResult. Checks if username is duplicate if it is
	 * redirects to an error page finally the user detail from the spring form is
	 * saved using the user service save method. The user gets directed to the login
	 * page
	 * 
	 * @param model        allows to add attributes to the model
	 * @param session      provides a way to identify active users accross the
	 *                     program
	 * @param returnedUser is the new user we get back from spring form
	 * @return login page if successful, otherwise redirects user to register page.
	 */
	@PostMapping(PROCESS_REGISTER)
	public String processRegisterPage(Model model, HttpSession session,
			@Valid @ModelAttribute(ACTIVE_USER) User returnedUser) {
		log.info("process reg user before: " + returnedUser);

		if (userService.usernameExist(returnedUser.getUsername())) {
			return USERNAME_ALREADY_EXIST;
		}

		returnedUser = userService.save(returnedUser);
		log.info("process reg user after: " + returnedUser);
		log.info("successful registeration");
		return GET_LOGIN;
	}

	/**
	 * Handler method to get the login page
	 * 
	 * @return it returns the login jsp file
	 */
	@GetMapping(GET_LOGIN)
	public String getLoginPage() {

		return GET_LOGIN;
	}

	/**
	 * Handler method to process user login form. checks the username and password
	 * to see if it exist in the database if the password and username match adds
	 * the user to the session.
	 * 
	 * @param model        allows to add attributes to the model
	 * @param returnedUser is the user we get back from login spring form
	 * @param session      provides a way to identify active users accross the
	 *                     program
	 * 
	 * @return logged in user details if successful, otherwise redirect to login
	 *         page.
	 */
	@PostMapping(PROCCESS_LOGIN)
	public String processLoginPage(Model model, User returnedUser, HttpSession session) {

		returnedUser = userService.checkLogin(returnedUser);

		if (returnedUser != null) {
			session.setAttribute(ACTIVE_USER, returnedUser);
			return REDIRECT_MOVIE_LIST;
		} else {
			model.addAttribute("invalidLogin", "Incorrect username or password");
			return GET_LOGIN;
		}
	}

	/**
	 * @param model   allows to add attributes to the model
	 * @param session provides a way to identify active users accross the program
	 * @param user    the active user in session to be deleted
	 * @return delete message jsp page to confirm the user is deleted
	 */
	@GetMapping(GET_DELETE)
	public String deleteUser(Model model, HttpSession session, User user) {

		User activeUser = (User) session.getAttribute(ACTIVE_USER);

		if (activeUser != null) {
			log.info("active user to be deleted " + activeUser);
			userService.remove(activeUser);
			session.invalidate();
			log.info("user deleted " + user);
			model.addAttribute("message", "Your account was deleted");
			return GET_DELETE_MESSAGE;
		}
		model.addAttribute("errorNoActiveUSer", "There is no active user in session");
		return GET_DELETE_MESSAGE;

	}

	/** Method to delete a user based on their id
	 * @param id the user id to be deleted
	 * @param user the user to be deleted 
	 * @param session  provides a way to identify active users accross the program
	 * @param model allows to add attributes to the model
	 * @return if successful redirects to the checkUser JSP page, if user delete selfe an error page appears
	 */
	@GetMapping("/userList/{id}")
	public String deleteUsers(@PathVariable("id") int id, User user, HttpSession session, Model model) {
		User activeUser = (User) session.getAttribute(ACTIVE_USER);

		if (activeUser.getId() != id) {
			userService.removeById(id);
			return "redirect:/checkUser";	
		}
		model.addAttribute("selfDelete", "You Just Deleted Yourself");
		userService.removeById(id);
		session.invalidate();
		return "deleteError";
	}

	/**
	 * Handler method to get the check user page. this page contains the active user
	 * details
	 * 
	 * @return checkUser jsp file to show active user details
	 */
	@GetMapping(GET_CHECK_USER)
	public String getCheckUserPge(Model model, HttpSession session) {
		model.addAttribute("userList", userService.listAll());
		User activeUser = (User) session.getAttribute(ACTIVE_USER);
		if (activeUser != null) {
			log.info(activeUser + "Active user in check user rened movies");
			log.info("Rented Movies" + rentalService.findByUserId(activeUser.getId()));
			model.addAttribute("rented", rentalService.findByUserId(activeUser.getId()));
			return GET_CHECK_USER;
		}

		return REDIRECT_LOGIN;
	}
	
	/** Handler method to get Update form page
	 * @param model allows to add attributes to the model
	 * @param id of the user to be updated
	 * @return Update page jsp
	 */
	@GetMapping("/userList/edit/{id}")
	public String updateUserById(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.get(id));

		return "/update";
	}
	
	/** Handler method to process the updated user form.
	 * @param id of the user to be updated and processed
	 * @param model allows to add attributes to the model
	 * @param session provides a way to identify active users accross the program
	 * @param returnedUser is the  user we get back from Update spring form
	 * @return updateSuccess jsp page to inform user 
	 */
	@PostMapping("/userList/pp/{id}")
	public String processUserUpdate(@PathVariable("id") int id, Model model, HttpSession session,
			@ModelAttribute("user") User returnedUser) {
		log.info("processUpdate");
		User activeUser = userService.get(id);
		if (userService.usernameExist(returnedUser.getUsername())) {
			return USERNAME_ALREADY_EXIST;
		}
		if (activeUser != null) {
			
			log.info("----------Active user------------");
			log.info("Active user to be updated " + activeUser);
			log.info("------------returned user--");
			log.info("returned user before updating" + returnedUser);
			activeUser = userService.update(activeUser, returnedUser);
			session.setAttribute(ACTIVE_USER, activeUser);
			log.info("Active User after updating: " + activeUser);
			session.setAttribute(ACTIVE_USER, activeUser);
			log.info("Updated user");
			model.addAttribute("updateSuccess", "The user was successfully updated" + activeUser.getUsername());

			
		}

		return GET_UPDATE_SUCCESS;
	
	}

}
