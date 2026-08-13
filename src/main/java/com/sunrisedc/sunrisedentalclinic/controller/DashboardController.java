package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.sunrisedc.sunrisedentalclinic.model.Staff;

//Routes each role to their dashboard
@WebServlet({"/manager/Mdashboard", "/receptionist/Rdashboard", "/dentist/Ddashboard"})
public class DashboardController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Staff user = (Staff) session.getAttribute("user");
        switch (user.getRole()) {
        case MANAGER -> request.getRequestDispatcher("/WEB-INF/view/manager/Mdashboard.jsp").forward(request, response);
        case RECEPTIONIST -> request.getRequestDispatcher("/WEB-INF/view/receptionist/Rdashboard.jsp").forward(request, response);
        case DENTIST -> request.getRequestDispatcher("/WEB-INF/view/dentist/Ddashboard.jsp").forward(request, response);
        }
    }
}
