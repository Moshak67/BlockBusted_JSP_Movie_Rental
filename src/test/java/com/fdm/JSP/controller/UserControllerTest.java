package com.fdm.JSP.controller;

import com.fdm.JSP.model.User;
import com.fdm.JSP.service.RentalService;
import com.fdm.JSP.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService mockUserService;
    @Mock
    private RentalService mockRentalService;

    @Mock
    HttpSession mockSession;

    @Mock
    Model mockModel;

    @Mock
    User mockUser;

    private UserController userController;
    private User testUser;

    @BeforeEach
    void setUp() {
        userController = new UserController(mockUserService, mockRentalService);
        testUser = new User("mo", "Mohammad", "Shakir", "1234", "2@g", "customer");
        testUser.setId(1);
    }

    @Test
    void testGetRegisterPage() {


        // Run the test
        final String result = userController.getRegisterPage(mockModel);

        // Verify the results
        assertEquals("/register", result);
    }

    @Test
    void testProcessRegisterPage() {
        // Setup

        final User returnedUser = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserService.usernameExist("username")).thenReturn(false);

        // Configure UserService.save(...).
        final User user = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserService.save(any(User.class))).thenReturn(user);

        // Run the test
        final String result = userController.processRegisterPage(mockModel, mockSession, returnedUser);

        // Verify the results
        assertEquals("/login", result);
    }

    @Test
    void testGetLoginPage() {
        // Setup
        // Run the test
        final String result = userController.getLoginPage();

        // Verify the results
        assertEquals("/login", result);
    }



    @Test
    void testProcessLoginPage_UserServiceReturnsNull() {
        // Setup

        final User returnedUser = new User("username", "fName", "lName", "password", "email", "role");

        when(mockUserService.checkLogin(any(User.class))).thenReturn(null);

        // Run the test
        final String result = userController.processLoginPage(mockModel, returnedUser, mockSession);

        // Verify the results
        assertEquals("/login", result);
    }






    @Test
    void testUpdateUserById() {
        // Setup


        // Configure UserService.get(...).
        final User user = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserService.get(0)).thenReturn(user);

        // Run the test
        final String result = userController.updateUserById(0, mockModel);

        // Verify the results
        assertEquals("/update", result);
    }

    @Test
    void testProcessUserUpdate() {
        // Setup

        final User returnedUser = new User("username", "fName", "lName", "password", "email", "role");

        // Configure UserService.get(...).
        final User user = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserService.get(0)).thenReturn(user);

        when(mockUserService.usernameExist("username")).thenReturn(false);

        // Configure UserService.update(...).
        final User user1 = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserService.update(any(User.class), any(User.class))).thenReturn(user1);

        // Run the test
        final String result = userController.processUserUpdate(0, mockModel, mockSession, returnedUser);

        // Verify the results
        assertEquals("/updateSuccess", result);
    }

    @Test
    void testProcessUserUpdate_UserServiceGetReturnsNull() {
        // Setup

        User returnedUser = new User("username", "fName", "lName", "password", "email", "role");
        when(mockUserService.get(0)).thenReturn(null);
        when(mockUserService.usernameExist("username")).thenReturn(false);


        // Run the test
        String result = userController.processUserUpdate(0, mockModel, mockSession, returnedUser);

        // Verify the results
        assertEquals("/updateSuccess", result);
    }

    @Test
    void test_get_registeration_returns_registration_jsp() {
        // Arrange
        // Act
        String result = userController.getRegisterPage(mockModel);
        // Assert
        assertEquals(userController.GET_REGISTER, result);
    }

    @Test
    void test_get_login_returns_login_jsp() {
        // Arrange
        // Act
        String result = userController.getLoginPage();
        // Assert
        assertEquals(userController.GET_LOGIN, result);
    }

    @Test
    void test_get_Check_User_Page_returns_checkUser_jsp() {
        // Arrange
        // Act
        String result = userController.getCheckUserPge(mockModel,mockSession);
        // Assert
        assertEquals(userController.REDIRECT_LOGIN, result);
    }



    @Test
    void test_get_Update_Page_returns_update_jsp() {
        // Arrange
        // Act
        String result = userController.updateUserById(1, mockModel);
        // Assert

        assertEquals(userController.GET_UPDATE, result);
    }


//	Processing Method Tests ---------------


    @Test
    void test_processRegisterPage() {


        User user = new User("mo", "mo", "mo", "mo", "mo","customer");
        String result = userController.processRegisterPage(mockModel, mockSession, user);


        InOrder order = inOrder(mockUserService);



        order.verify(mockUserService, times(1)).usernameExist(user.getUsername());

        order.verify(mockUserService, times(1)).save(user);

        assertEquals(userController.GET_LOGIN, result);
    }

    @Test
    void testGetRegisterPage_Calls_modeAddAttribute() {
        String result = userController.getRegisterPage(mockModel);

        verify(mockModel, times(1)).addAttribute(eq("user"), isA(User.class));
    }


    @Test
    void test_processRegisterPage_with_alreadyTaken_Username_returns_usernameAlreadyExist_page() {

        when(mockUserService.usernameExist(mockUser.getUsername())).thenReturn(true);
        String result = userController.processRegisterPage(mockModel, mockSession, mockUser);

        verify(mockUserService, times(1)).usernameExist(mockUser.getUsername());
        assertEquals(userController.USERNAME_ALREADY_EXIST, result);
    }

    @Test
    void test_ProcessLoginPage_calls_checkLogin_once() {
        String result = userController.processLoginPage(mockModel, testUser, mockSession);
        verify(mockUserService).checkLogin(testUser);

    }

    @Test
    void test_ProcessLoginPage_returns_aUser() {

        when(mockUserService.checkLogin(testUser)).thenReturn(testUser);

        User loggedIn = mockUserService.checkLogin(testUser);

        assertNotNull(loggedIn);

    }
    @Test
    void test_checkLogin_returns_existing_user_in_DB_and_Returns_checkUser_Page() {
        User user = new User("dddd", "mo", "mo", "1", "mo","customer");
        User existing = new User("dddd", "mo", "mo", "1", "mo","customer");
        when(mockUserService.checkLogin(user)).thenReturn(existing);
        String result = userController.processLoginPage(mockModel, user, mockSession);
        assertEquals(userController.REDIRECT_MOVIE_LIST, result);

    }

    @Test
    void test_checkLogin_with_null_user_redirects_user_to_loginPage() {
        User user = new User("dddd", "mo", "mo", "1", "mo","customer");
        when(mockUserService.checkLogin(user)).thenReturn(null);
        String result = userController.processLoginPage(mockModel, user, mockSession);

        assertEquals(userController.GET_LOGIN, result);

    }

    @Test
    void testProcessRegister_returns_getRegisterPage_when_formFields_are_unfilled() {

        String result = userController.processRegisterPage(mockModel, mockSession, mockUser);
        assertEquals(userController.GET_LOGIN, result);
    }





    @Test
    void test_deleteUser() {
        when(mockSession.getAttribute(userController.ACTIVE_USER)).thenReturn(mockUser);

        String result = userController.deleteUser(mockModel, mockSession, mockUser);

        verify(mockUserService,times(1)).remove(mockUser);
        verify(mockSession, times(1)).invalidate();
        assertEquals(userController.GET_DELETE_MESSAGE, result);
    }

    @Test
    void test_deleteUser_with_Null_value() {
        when(mockSession.getAttribute(userController.ACTIVE_USER)).thenReturn(null);

        String result = userController.deleteUser(mockModel, mockSession, null);
        assertEquals(userController.GET_DELETE_MESSAGE, result);
    }
}
