package com.fdm.JSP.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.JSP.Repository.MovieRepository;
import com.fdm.JSP.model.Movie;


import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
	
	private MovieRepository movieRepository;
	private Log log = LogFactory.getLog(MovieService.class);
	
	@Autowired
	public MovieService(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}
	
	public Movie save(Movie movie) {
		log.info("Movie was saved in db " +movie);
		
		return movieRepository.save(movie);
	}
	
	public Movie update(Movie activeMovie,Movie returnedMovie) {
		Optional<Movie> toUpdate = movieRepository.findById(activeMovie.getId());
		if (toUpdate.isEmpty()) {
			return null;
		}
		Movie updated = toUpdate.get();
		updated.setName(returnedMovie.getName());
		log.info("Movie updated " + updated);
		movieRepository.save(updated);
		return updated;
	}
	
	public List<Movie> getAllMovies(){
		
		return movieRepository.findAll();
	}
	
	public void remove(int id) {
		log.info("Movie id deleted " + id);
		movieRepository.deleteById(id);
	}
	
	public Movie getMovieById(int id) {
		Optional<Movie> returnedMovie = movieRepository.findById(id);
		return returnedMovie.get();
	}
	
	
	

}
