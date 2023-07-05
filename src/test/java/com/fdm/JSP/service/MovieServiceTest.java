package com.fdm.JSP.service;

import com.fdm.JSP.Repository.MovieRepository;
import com.fdm.JSP.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository mockMovieRepository;

    private MovieService movieServiceUnderTest;

    @BeforeEach
    void setUp() {
        movieServiceUnderTest = new MovieService(mockMovieRepository);
    }

    @Test
    void testSave() {
        // Setup
        final Movie movie = new Movie("name", "genre", 2020, "director", 0.0);

        // Configure MovieRepository.save(...).
        final Movie movie1 = new Movie("name", "genre", 2020, "director", 0.0);
        when(mockMovieRepository.save(any(Movie.class))).thenReturn(movie1);

        // Run the test
        final Movie result = movieServiceUnderTest.save(movie);

        // Verify the results
    }

    @Test
    void testSave_MovieRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final Movie movie = new Movie("name", "genre", 2020, "director", 0.0);
        when(mockMovieRepository.save(any(Movie.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThrows(OptimisticLockingFailureException.class, () -> movieServiceUnderTest.save(movie));
    }

    @Test
    void testUpdate() {
        // Setup
        final Movie activeMovie = new Movie("name", "genre", 2020, "director", 0.0);
        final Movie returnedMovie = new Movie("name", "genre", 2020, "director", 0.0);

        // Configure MovieRepository.findById(...).
        final Optional<Movie> movie = Optional.of(new Movie("name", "genre", 2020, "director", 0.0));
        when(mockMovieRepository.findById(0)).thenReturn(movie);

        // Configure MovieRepository.save(...).
        final Movie movie1 = new Movie("name", "genre", 2020, "director", 0.0);
        when(mockMovieRepository.save(any(Movie.class))).thenReturn(movie1);

        // Run the test
        final Movie result = movieServiceUnderTest.update(activeMovie, returnedMovie);

        // Verify the results
        verify(mockMovieRepository).save(any(Movie.class));
    }

    @Test
    void testUpdate_MovieRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Movie activeMovie = new Movie("name", "genre", 2020, "director", 0.0);
        final Movie returnedMovie = new Movie("name", "genre", 2020, "director", 0.0);
        when(mockMovieRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Movie result = movieServiceUnderTest.update(activeMovie, returnedMovie);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testUpdate_MovieRepositorySaveThrowsOptimisticLockingFailureException() {
        // Setup
        final Movie activeMovie = new Movie("name", "genre", 2020, "director", 0.0);
        final Movie returnedMovie = new Movie("name", "genre", 2020, "director", 0.0);

        // Configure MovieRepository.findById(...).
        final Optional<Movie> movie = Optional.of(new Movie("name", "genre", 2020, "director", 0.0));
        when(mockMovieRepository.findById(0)).thenReturn(movie);

        when(mockMovieRepository.save(any(Movie.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThrows(OptimisticLockingFailureException.class,
                () -> movieServiceUnderTest.update(activeMovie, returnedMovie));
    }

    @Test
    void testGetAllMovies() {
        // Setup
        // Configure MovieRepository.findAll(...).
        final List<Movie> movies = List.of(new Movie("name", "genre", 2020, "director", 0.0));
        when(mockMovieRepository.findAll()).thenReturn(movies);

        // Run the test
        final List<Movie> result = movieServiceUnderTest.getAllMovies();

        // Verify the results
    }

    @Test
    void testGetAllMovies_MovieRepositoryReturnsNoItems() {
        // Setup
        when(mockMovieRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Movie> result = movieServiceUnderTest.getAllMovies();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testRemove() {
        // Setup
        // Run the test
        movieServiceUnderTest.remove(0);

        // Verify the results
        verify(mockMovieRepository).deleteById(0);
    }

    @Test
    void testGetMovieById() {
        // Setup
        // Configure MovieRepository.findById(...).
        final Optional<Movie> movie = Optional.of(new Movie("name", "genre", 2020, "director", 0.0));
        when(mockMovieRepository.findById(0)).thenReturn(movie);

        // Run the test
        final Movie result = movieServiceUnderTest.getMovieById(0);

        // Verify the results
    }

    @Test
    void testGetMovieById_MovieRepositoryReturnsAbsent() {
        // Setup
        when(mockMovieRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> movieServiceUnderTest.getMovieById(0));
    }
}
