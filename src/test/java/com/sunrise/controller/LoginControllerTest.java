package com.sunrise.controller;

import com.sunrisedc.sunrisedentalclinic.controller.LoginController;
import com.sunrisedc.sunrisedentalclinic.service.AuthenticationService;
import com.sunrisedc.sunrisedentalclinic.model.Manager;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    private AuthenticationService authService;
    private LoginController controller;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        authService = mock(AuthenticationService.class);
        controller = new LoginController(authService);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }
    @Test
    void forwardToLoginPage() throws Exception {
        when(request.getRequestDispatcher("/WEB-INF/view/login.jsp")).thenReturn(dispatcher);

        controller.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    void successfullyRedirectToLoginPage() throws Exception {
        Staff user = new Manager(1, "manager", "hash", "Mgr", "m@sunrise.lk", "071");
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("username")).thenReturn("manager");
        when(request.getParameter("password")).thenReturn("Password@123");
        when(authService.login("manager", "Password@123")).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("");

        controller.doPost(request, response);

        verify(session).setAttribute("user", user);
        verify(response).sendRedirect("/manager/Mdashboard");
    }

    @Test
    void unsuccessfullyRedirectToLoginPage() throws Exception {
        when(request.getParameter("username")).thenReturn("manager");
        when(request.getParameter("password")).thenReturn("wrongPassword");
        when(authService.login("manager", "wrongPassword")).thenReturn(null);
        when(request.getRequestDispatcher("/WEB-INF/view/login.jsp")).thenReturn(dispatcher);

        controller.doPost(request, response);

        verify(request).setAttribute("error", "Invalid username or password");
        verify(dispatcher).forward(request, response);
    }
}
