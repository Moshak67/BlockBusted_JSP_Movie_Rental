package com.fdm.JSP.controller;

import com.fdm.JSP.model.Movie;
import com.fdm.JSP.model.Rental;
import com.fdm.JSP.model.User;
import com.fdm.JSP.service.MovieService;
import com.fdm.JSP.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    private MovieService mockMovieService;
    @Mock
    private RentalService mockRentalService;

    @Mock
    HttpSession session;

    @Mock
    Model model;

    private MovieController movieController;

    @BeforeEach
    void setUp() {
        movieController = new MovieController(mockMovieService, mockRentalService);
    }

    @Test
    void testUpdateMovie() {
        // Arrange



        Movie movie = new Movie("name", "genre", 2020, "director", 0.0);
        when(mockMovieService.getMovieById(0)).thenReturn(movie);

        // Act
        String result = movieController.updateMovie(0, model);

        // Assert
        assertEquals("editMovie", result);
    }

    @Test
    void testProcessMovieUpdate() {
        // Arrange

        Movie returnedMovie = new Movie("name", "genre", 2020, "director", 0.0);


        Movie movie = new Movie("name", "genre", 2020, "director", 0.0);
        when(mockMovieService.getMovieById(0)).thenReturn(movie);


        Movie movie1 = new Movie("name", "genre", 2020, "director", 0.0);
        when(mockMovieService.update(any(Movie.class), any(Movie.class))).thenReturn(movie1);

        // Act
        String result = movieController.processMovieUpdate(0, model, returnedMovie);

        // Assert

        assertEquals("redirect:/movieList", result);
        verify(mockMovieService).update(any(Movie.class), any(Movie.class));
    }

    @Test
    void testGetAddMoviePage() {
        // Arrange
        Model model = new ConcurrentModel();

        //Act
       String result = movieController.getAddMoviePage(model);

        // Assert
        assertEquals("/addMovie", result);
    }

    @Test
    void testProcessAddMovie() {

        Model model = new ConcurrentModel();
        Movie returnedMovie = new Movie("name", "genre", 2020, "director", 0.0);


        Movie movie = new Movie("name", "genre", 2020, "director", 0.0);
        when(mockMovieService.save(any(Movie.class))).thenReturn(movie);

        String result = movieController.processAddMovie(model, returnedMovie);

        assertEquals("redirect:/movieList", result);
    }

    @Test
    void testGetMovieList() {

        Model model = new ConcurrentModel();
        List<Movie> movies = List.of(new Movie("name", "genre", 2020, "director", 0.0));
        when(mockMovieService.getAllMovies()).thenReturn(movies);
        String result = movieController.getMovieList(model);
        assertEquals("movieList", result);
    }

    @Test
    void testGetMovieList_MovieServiceReturnsNoItems() {

        when(mockMovieService.getAllMovies()).thenReturn(Collections.emptyList());
        String result = movieController.getMovieList(model);
        assertEquals("movieList", result);
    }

    @Test
    void testDeleteMovie() {

        String result = movieController.deleteMovie(0);
        assertEquals("redirect:/movieList", result);
        verify(mockMovieService).remove(0);
    }


}
