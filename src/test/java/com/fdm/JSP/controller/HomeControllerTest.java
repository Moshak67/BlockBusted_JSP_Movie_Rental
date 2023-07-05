package com.fdm.JSP.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    private HomeController homeControllerUnderTest;
    @Mock
    HttpSession mockSession;

    @Mock
    Model mockModel;

    @BeforeEach
    void setUp() {
        homeControllerUnderTest = new HomeController();
    }

    @Test
    void testGetGreetingPage() {

        String result = homeControllerUnderTest.getGreetingPage();


        assertEquals("/greeting", result);
    }

}
