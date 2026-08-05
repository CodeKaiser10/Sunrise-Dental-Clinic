package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import com.sunrisedc.sunrisedentalclinic.service.AuthenticationService;

//Servlet mapped to /login to show the form and handles the login attempt
@WebServlet("/login")
public class LoginController extends HttpServlet {

    private AuthenticationService authService;

    //get the shared auth service when the servlet loads
    public void init() throws ServletException {
        authService = AuthenticationService.getInstance();
    }

    //GET request
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    //POST request
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String passwordHash = request.getParameter("password");

        Staff user = authService.login(username, passwordHash);

        if (user != null) {
            //store the user in the session and direct to their dashboard
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + user.getDashboard());
        } else {
            //if failed redirect to form and show error message
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }
}
