package com.fdm.JSP.Setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;


import com.fdm.JSP.model.Movie;
import com.fdm.JSP.model.Rental;
import com.fdm.JSP.model.User;
import com.fdm.JSP.service.MovieService;
import com.fdm.JSP.service.RentalService;
import com.fdm.JSP.service.UserService;

@Component
public class DataLoader implements ApplicationRunner {

	private MovieService movieService;
	
	private RentalService rentalService;
	
	private UserService userService;
	
	
	private Log log = LogFactory.getLog(DataLoader.class);

	// you need @modifying for any class that is not a service
	@Override
	@Transactional
	@Modifying
	public void run(ApplicationArguments args) throws Exception {
		log.info("Starting setup");
		Movie movie1 = new Movie("The 400 Blows", "Crime", 19195988, "François Truffaut", 10);
		Movie movie2 = new Movie("Old Henry", "Action", 2021, "Potsy", 7);
		Movie movie3 = new Movie("David", "Comedy", 2020, "Zac Woods", 7);
		Movie movie4 = new Movie("Host", "Horror", 2020, "Rob Savage", 10);
		Movie movie5 = new Movie("Mosul", "Action", 2019, "Matthew Carnahan", 6);
		Movie movie6 = new Movie("Upgrade", "Action", 2018, "Leigh Whannell", 8);

		movie1 = movieService.save(movie1);
		movie2 = movieService.save(movie2);
		movie3 = movieService.save(movie3);
		movie4 = movieService.save(movie4);
		movie5 = movieService.save(movie5);
		movie6 = movieService.save(movie6);
		
		
		User customer = new User("mo", "Mohammad", "Shakir", "1234", "mo@hh.com", "customer");
		User admin = new User("admin", "Hossein", "Karimi", "123", "mos@hh.com", "admin");
		
		customer = userService.save(customer);
		admin = userService.save(admin);
		
		
		//List<Rental> newRental = new ArrayList<>();
		
		//newRental.add(rentalService.create(movie6, customer)); 
		//newRental.add(rentalService.create(movie2, customer));
		//newRental.add(rentalService.create(movie4, customer));
		
		//customer.setRentals(newRental);
		
		
		//rentalService.remove();
	

		movieService.getAllMovies().forEach(System.out::println);
		//rentalService.findAll().forEach(System.out::println);
		userService.listAll().forEach(System.out::println);
		
		log.info("Finished setup");
		log.info("http://localhost:8088");

	}

	@Autowired
	public DataLoader(MovieService movieService, RentalService rentalService, UserService userService) {
		super();
		this.movieService = movieService;
		this.rentalService = rentalService;
		this.userService = userService;
	}

}
