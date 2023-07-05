package com.fdm.JSP.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.JSP.Repository.RentalRepository;
import com.fdm.JSP.model.Movie;
import com.fdm.JSP.model.Rental;
import com.fdm.JSP.model.User;

/** Service class for the Rental class interacts with the Rental repository
 * @author Mohammad Shakir
 *
 */
@Service
public class RentalService {

	private RentalRepository rentalRepository;
	public LocalDate actualDate = LocalDate.now();
	
	private Log log = LogFactory.getLog(RentalService.class);

	/** constructor for dependency injection
	 * @param rentalRepository an instance of rental repo is injected into the service class
	 */
	@Autowired
	public RentalService(RentalRepository rentalRepository) {
		super();
		this.rentalRepository = rentalRepository;
	}
	
	/** Method to retrive all the rentals in the database
	 * @return list of rentals in the database
	 */
	public List<Rental> findAll() {
        return rentalRepository.findAll();
    }
	
	/**Method to create a rental in the database the rental duration is 2 days the rental expires in 2 days
	 * @param movie to be rented
	 * @param user who is renting the movie
	 * @return the rental detail
	 */
	public Rental create(Movie movie, User user) {
		
		Rental newRental = new Rental();
		log.info("user " + user + " renting " + movie);
		newRental.setMovie(movie);
		newRental.setUser(user);
		newRental.setRentalDate(actualDate);
		newRental.setExpirayDate(actualDate.plusDays(2));
		
		newRental = rentalRepository.save(newRental);
		
		log.info("the new rental detail " + newRental);
		
		return newRental;
	}
	
	/**
	 * Method to remove a rental after the rental period is expired checks the current date 
	 * the rental duration is 2 days 
	 * compares with the expiry date of the rental 
	 * if the current date is passed expiry date it removes the rental
	 */
	public String remove() {
		
		List<Rental> rentals = rentalRepository.findAll();
		
		for (Rental rental : rentals) {
			
			if (actualDate.isAfter(rental.getExpirayDate())) {
				long days = Duration.between(rental.getExpirayDate().atStartOfDay(), actualDate.atStartOfDay()).toDays();
				if (days > 0) {
					log.info("This rental is expired and is removed " + rental);
					rentalRepository.delete(rental);
					return "removed";
				}

		}
		
		
			log.info("There is still left of these rental " + rentals);
		}
		return "not removed";
	}
	
	/** Finds the the rental details of a user 
	 * @param id user id to retrive their rental details
	 * @return a List of rentals the user holds
	 */
	public List<Rental> findByUserId(int id){
		return rentalRepository.findByUser_idEquals(id);
	}
	
}
