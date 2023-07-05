package com.fdm.JSP.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdm.JSP.model.Movie;
import com.fdm.JSP.model.User;
import com.fdm.JSP.service.MovieService;
import com.fdm.JSP.service.RentalService;

/** Controller for the Movie class it interacts with  MovieService and RentalService classes 
 * 	Methods implemented are updateMovie to update a movie name based on the movie id, process this update with postmapping 
 * 	Methods to Add movie and processing the updated movie with postmapping
 * 	Method to get a list of available movies 
 * 	Method to delete a movie from database based on the id
 * 	Method to rent a movie based on the movie id
 * 	these methods return corresponding JSP pages in the web browser and allow user to interact with the system
 * @author Mohammad Shakir
 *
 */
@Controller
public class MovieController {
	private MovieService movieService;
	private RentalService rentalService;
	private Log log = LogFactory.getLog(MovieController.class);

	/** Controller Constructor for dependency injection , allows the methods to used via the controller
	 * @param movieService an instance of movieService class is injected into movie controller as an argument
	 * @param rentalService an instance of rentalService class is injected into movie controller as an argument
	 */
	@Autowired
	public MovieController(MovieService movieService, RentalService rentalService) {
		super();
		this.movieService = movieService;
		this.rentalService = rentalService;
	}
	
	/** Handler method to get the update movie page based on the movie id 
	 * @param id the pathvariable to a movie to be updated
	 * @param model holder for model attributes
	 * @return editMovie JSP page including a form 
	 */
	@GetMapping("/movieList/edit/{id}")
	public String updateMovie(@PathVariable("id") int id, Model model) {
		model.addAttribute("movie", movieService.getMovieById(id));
		
		return "editMovie";
	}
	/** Handler method to process a movie update using Post method 
	 * 	retrives the the movie from database performs update on the returned movie 
	 * 	saves the new details to the database
	 * @param id pathvariable to the movie that is about to be updated
	 * @param model holder for model attributes
	 * @param returnedMovie new details of the movie to be inserted to the database
	 * @return redirects to the movieList JSP page.
	 */
	@PostMapping("/movieList/pp/{id}")
	public String processMovieUpdate(@PathVariable("id") int id,Model model,
			@ModelAttribute("movie") Movie returnedMovie) {
				
		Movie currMovie = movieService.getMovieById(id);	
		log.info("Process movie update" + returnedMovie + " " + currMovie );		
		movieService.update(currMovie, returnedMovie);			
		return "redirect:/movieList";
	}
	
	/** Handler Method to get the add movie page 
	 * @param model holder for model attributes
	 * @return addMovie JSP page which has a form for the user to input movie details
	 */
	@GetMapping("/addMovie")
	public String getAddMoviePage(Model model) {
		Movie movie = new Movie();
		log.info("get movie page " + movie);
		model.addAttribute("movie", movie);
		return "/addMovie";
	}
	
	/** Handler Method to Process add movie method using post method
	 * 	saves a movie into the database via a form 
	 * @param model holder for model attributes
	 * @param returnedMovie new movie to be added in the database
	 * @return movie list JSP page 
	 */
	@PostMapping("/add")
	public String processAddMovie(Model model, @ModelAttribute("movie") Movie returnedMovie) {
		returnedMovie = movieService.save(returnedMovie);
		log.info( returnedMovie + " was added to the database");
		return "redirect:/movieList";
	}
	
	/** Handler method to get a list of movies in the database
	 * @param model holder for model attributes
	 * @return a List of Movies in the database , movieList JSP page
	 */
	@GetMapping("/movieList")
	public String getMovieList(Model model) {
		model.addAttribute("movieList", movieService.getAllMovies());
		return "movieList";
	}
	
	
	
	/** Handler method to delete a movie from the db based on the id
	 * @param id the movie id
	 * @return redirects to movieList JSP Page 
	 */
	@GetMapping("/movieList/{id}")
	public String deleteMovie(@PathVariable("id") int id) {
		movieService.remove(id);
		return "redirect:/movieList";
	}
	
	/** Handler Method to rent a movie to a customer based on the movie id
	 * @param id the id of the movie to be rented
	 * @param session  provides a way to identify the current users wanting to rent the movie
	 * @return redirects to the users JSP page with the list of movies they have on rent
	 */
	@GetMapping("/movieList/rent/{id}")
	public String rentMovie(@PathVariable("id") int id, HttpSession session) {
		User activeUser = (User) session.getAttribute("user");
		rentalService.create(movieService.getMovieById(id), activeUser);
		rentalService.remove();
		return "redirect:/checkUser";
	}

}
