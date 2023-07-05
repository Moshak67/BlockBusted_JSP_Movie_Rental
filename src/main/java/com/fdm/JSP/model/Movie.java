package com.fdm.JSP.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/** Movie Entity Class has One to many relationship with the rental class. the cascade type is set to all so when a user is deleted from the system the movies are also deleted from the rental
 * @author Mohammad Shakir
 *
 */
@Entity
public class Movie {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String genre;
	private int year;
	private String director;
	private double rating;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<Rental> rentals;

	/**
	 * Zero Constructor 
	 */
	public Movie() {
		super();
		
	}
	
	
	/** All Args Constructor to instantiate a movie
	 * @param name initial movie name	
	 * @param genre initial genre 
	 * @param year initial year
	 * @param director initial director
	 * @param rating initial movie rate
	 */
	public Movie(String name, String genre, int year, String director, double rating) {
		super();
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.director = director;
		this.rating = rating;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", genre=" + genre + ", year=" + year + ", director=" + director
				+ ", rating=" + rating + "]";
	}
	
	

}
