package com.fdm.JSP.service;

import com.fdm.JSP.Repository.UserRepository;
import com.fdm.JSP.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    @Mock
    User mockUser;

    private UserService userService;

    private User newUser;

    @BeforeEach
    void setUp() {
        userService = new UserService(mockUserRepo);
        newUser = new User("moshak", "Mohammad", "Shakir", "1234", "email@gmail", "admin");
    }

    @Test
    void testUsernameExistReturnsTrue() {
        // Arrange
        Optional<User> user = Optional.of(newUser);
        when(mockUserRepo.findByUsername("moshak")).thenReturn(user);

        // Act
        boolean result = userService.usernameExist("moshak");

        // Assert
        assertTrue(result);
    }

    @Test
    void testUsernameExist_UserRepositoryReturnsFalse() {
        // Arrange
        when(mockUserRepo.findByUsername("username")).thenReturn(Optional.empty());

        // Act
        boolean result = userService.usernameExist("username");

        // Assert
        assertFalse(result);
    }

    @Test
    void testSave() {
        // Arrange
        User userToSave = newUser;


        User user = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserRepo.save(userToSave)).thenReturn(userToSave);

        // act
        User result;
        result = userService.save(userToSave);

        // assert
        verify(mockUserRepo).save(userToSave);
        assertEquals(result,newUser);
    }


    @Test
    void testUpdate() {
        // Arrange
        User activeUser = new User("username", "fName", "lName", "password", "email", "role");
        User returnedUser = newUser;


        Optional<User> user = Optional.of(activeUser);
        when(mockUserRepo.findById(0)).thenReturn(user);

        // Act
        User result = userService.update(activeUser, returnedUser);

        // Assert
        verify(mockUserRepo).save(result);
        assertEquals(result.getUsername(),returnedUser.getUsername());
    }

    @Test
    void testUpdate_UserRepositoryFindByIdReturnsNull() {
        // Arrange
        User activeUser = newUser;
        User returnedUser = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserRepo.findById(0)).thenReturn(Optional.empty());

        // Act
        User result = userService.update(activeUser, returnedUser);

        // assert
        assertNull(result);
        verify(mockUserRepo).findById(0);
    }

    @Test
    void testRemove() {
        // Arrange
        User user = newUser;

        // Act
        User result = userService.remove(user);

        // assert
        verify(mockUserRepo).deleteById(0);
        assertNull(result);
    }

    @Test
    void testRemoveById() {
        // Arrange
        Optional<User> user = Optional.of(newUser);
        when(mockUserRepo.findById(newUser.getId())).thenReturn(user);

        // Act
        userService.removeById(newUser.getId());

        // assert
        verify(mockUserRepo).deleteById(0);
    }

    @Test
    void testRemoveById_UserRepositoryFindByIdReturnsNothing() {
        // arrange
        when(mockUserRepo.findById(0)).thenReturn(Optional.empty());

        // Act
        userService.removeById(0);

        // assert
        verify(mockUserRepo).deleteById(0);

    }

    @Test
    void testListAll() {

        // Arrange
        List<User> users = List.of(newUser);
        when(mockUserRepo.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.listAll();

        // assert
        verify(mockUserRepo).findAll();
        assertEquals(result.size(),1);
    }

    @Test
    void testListAll_UserRepositoryReturnsNoItems() {
        // Arrange
        when(mockUserRepo.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<User> result = userService.listAll();

        // Assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGet() {

        // Arrange
        Optional<User> user = Optional.of(newUser);
        when(mockUserRepo.findById(newUser.getId())).thenReturn(user);

        // Act
        User result = userService.get(newUser.getId());

        // assert
        verify(mockUserRepo).findById(newUser.getId());
        assertEquals(result,newUser);
    }


    @Test
    void testCheckLogin() {
        // Arrange
        User user = newUser;


        Optional<User> user1 = Optional.of(user);
        when(mockUserRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user1);

        // Act
        User result = userService.checkLogin(user);

        // Assert
        verify(mockUserRepo).findByUsernameAndPassword(user.getUsername(),user.getPassword());
        assertEquals(result, user);
    }

    @Test
    void testCheckLogin_UserRepositoryReturnsNull() {
        // Arrange
        User user = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserRepo.findByUsernameAndPassword("username", "password")).thenReturn(Optional.empty());

        // Act
        User result = userService.checkLogin(user);

        // Assert
        assertNull(result);
        verify(mockUserRepo).findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
