package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.service.UserService;

// Read-only list of dentists (users with role DENTIST) for manager and receptionist.
@WebServlet({"/manager/dentists", "/receptionist/dentists"})
public class DentistController extends HttpServlet {

    private UserService userService;

    public DentistController() {}

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("dentists", userService.getUsersByRole("DENTIST"));

        // Route to the right folder based on who is viewing.
        if (request.getServletPath().startsWith("/manager")) {
            request.getRequestDispatcher("/WEB-INF/view/manager/dentists.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/view/receptionist/dentists.jsp").forward(request, response);
        }
    }
}
