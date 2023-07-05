package com.fdm.JSP.service;

import com.fdm.JSP.Repository.RentalRepository;
import com.fdm.JSP.model.Movie;
import com.fdm.JSP.model.Rental;
import com.fdm.JSP.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Mock
    private RentalRepository mockRentalRepository;

    private RentalService rentalServiceUnderTest;

    @BeforeEach
    void setUp() {
        rentalServiceUnderTest = new RentalService(mockRentalRepository);
    }

    @Test
    void testFindAll() {
        // Setup
        // Configure RentalRepository.findAll(...).
        final List<Rental> rentals = List.of(
                new Rental(new Movie("name", "genre", 2020, "director", 0.0), LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 1), new User("username", "fName", "lName", "password", "email", "role")));
        when(mockRentalRepository.findAll()).thenReturn(rentals);

        // Run the test
        final List<Rental> result = rentalServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_RentalRepositoryReturnsNoItems() {
        // Setup
        when(mockRentalRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Rental> result = rentalServiceUnderTest.findAll();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testCreate() {
        // Setup
        final Movie movie = new Movie("name", "genre", 2020, "director", 0.0);
        final User user = new User("username", "fName", "lName", "password", "email", "role");

        // Configure RentalRepository.save(...).
        final Rental rental = new Rental(new Movie("name", "genre", 2020, "director", 0.0), LocalDate.of(2020, 1, 1),
                LocalDate.of(2020, 1, 1), new User("username", "fName", "lName", "password", "email", "role"));
        when(mockRentalRepository.save(any(Rental.class))).thenReturn(rental);

        // Run the test
        final Rental result = rentalServiceUnderTest.create(movie, user);

        // Verify the results
    }

    @Test
    void testCreate_RentalRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final Movie movie = new Movie("name", "genre", 2020, "director", 0.0);
        final User user = new User("username", "fName", "lName", "password", "email", "role");
        when(mockRentalRepository.save(any(Rental.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThrows(OptimisticLockingFailureException.class, () -> rentalServiceUnderTest.create(movie, user));
    }

    @Test
    void testRemove() {
        // Setup
        // Configure RentalRepository.findAll(...).
        final List<Rental> rentals = List.of(
                new Rental(new Movie("name", "genre", 2020, "director", 0.0), LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 1), new User("username", "fName", "lName", "password", "email", "role")));
        when(mockRentalRepository.findAll()).thenReturn(rentals);

        // Run the test
        String removeMessage = rentalServiceUnderTest.remove();

        // Verify the results

        assertEquals(removeMessage,"removed");
    }

    @Test
    void testRemove_RentalRepositoryFindAllReturnsNoItems() {
        // Setup
        LocalDate actualDate = LocalDate.now();
        final List<Rental> rentals = List.of(
                new Rental(new Movie("name", "genre", 2020, "director", 0.0), actualDate,
                        actualDate.plusDays(2), new User("username", "fName", "lName", "password", "email", "role")));
        when(mockRentalRepository.findAll()).thenReturn(rentals);

        // Run the test
        String removeMessage = rentalServiceUnderTest.remove();

        // Verify the results
        assertEquals(removeMessage,"not removed");
    }



    @Test
    void testFindByUserId() {
        // Setup
        // Configure RentalRepository.findByUser_idEquals(...).
        final List<Rental> rentals = List.of(
                new Rental(new Movie("name", "genre", 2020, "director", 0.0), LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 1), new User("username", "fName", "lName", "password", "email", "role")));
        when(mockRentalRepository.findByUser_idEquals(0)).thenReturn(rentals);

        // Run the test
        final List<Rental> result = rentalServiceUnderTest.findByUserId(0);

        // Verify the results
        verify(mockRentalRepository).findByUser_idEquals(0);
        assertEquals(result.size(),1);
    }

    @Test
    void testFindByUserId_RentalRepositoryReturnsNoItems() {
        // Setup
        when(mockRentalRepository.findByUser_idEquals(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Rental> result = rentalServiceUnderTest.findByUserId(0);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
