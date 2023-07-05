package com.fdm.JSP.model;

import javax.persistence.*;
import java.time.LocalDate;


/** Rental Entity has a Many to one relationship with User Entity and a Many to one relationship to the Movie entity 
 * @author Mohammad Shakir
 *
 */
@Entity
@Table(name = "rents")
public class Rental {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @Column(name = "rental_date")
    private LocalDate rentalDate;
    @Column(name = "return_date")
    private LocalDate expirayDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
	/**
	 * Zero Arg Constructor
	 */
	public Rental() {
		super();
		
	}

	/** All args Constructor
	 * @param movie instance of the movie class with all the movie detial
	 * @param rentalDate initial rental date of the movie
	 * @param expirayDate initial expiry date of a rental
	 * @param user the user renting the movie
	 */
	public Rental(Movie movie, LocalDate rentalDate, LocalDate expirayDate, User user) {
		super();
		this.movie = movie;
		this.rentalDate = rentalDate;
		this.expirayDate = expirayDate;
		this.user = user;
	}
	
	//getters setters

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	
	public LocalDate getExpirayDate() {
		return expirayDate;
	}


	public void setExpirayDate(LocalDate expirayDate) {
		this.expirayDate = expirayDate;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "Movie=" + movie.getName() + ", rentalDate=" + rentalDate + ", expirayDate=" + expirayDate
				+ ", Rented By=" + user.getUsername();
	}

	

}
