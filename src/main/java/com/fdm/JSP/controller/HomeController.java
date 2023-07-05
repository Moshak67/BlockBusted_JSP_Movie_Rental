package com.fdm.JSP.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdm.JSP.model.User;

@Controller
public class HomeController {
	private Log log = LogFactory.getLog(HomeController.class);
	final String GET_LOGGEDOUT = "/loggedout";
	final String ACTIVE_USER = "user";
	final String GET_LOGOUT = "/getLogout";
	final String GREETING_PAGE = "/greeting";
	final String GREETING_JSP = "/greeting";	
	
	/** handler method to get the index page
	 * @return index page jsp
	 */
	@GetMapping({ "/", GREETING_PAGE })
	public String getGreetingPage() {

		return GREETING_JSP;
	}

	/**
	 * Handler method to get the log out page. it removes the active user from the
	 * session
	 * 
	 * @param session provides a way to identify active users accross the program
	 * @return logged out jsp page to inform user they logged out
	 */
	@GetMapping(GET_LOGOUT)
	public String getLogoutPage(HttpSession session, Model model) {

		User activeUser = (User) session.getAttribute(ACTIVE_USER);
		if (activeUser != null) {
			session.invalidate();
			model.addAttribute("logoutMessage", "Logging out!!");
			log.info("logged out");
		}
		model.addAttribute("alreadyLoggedout", "You are already Logged out");

		return GET_LOGGEDOUT;
	}

}
