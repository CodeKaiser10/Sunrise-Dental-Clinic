package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.model.*;
import com.sunrisedc.sunrisedentalclinic.service.UserService;

@WebServlet("/manager/users")
public class UserManagementController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            String action = request.getParameter("action");

            //load the one user and show the pre-filled form
            if ("edit".equals(action)) {
                String username = request.getParameter("username");
                Staff user = userService.findUser(username);
                request.setAttribute("editUser", user);
                request.getRequestDispatcher("/WEB-INF/view/manager/user-edit.jsp").forward(request, response);
                return;
            }

            //show the full staff list
            request.setAttribute("users", userService.getAllUsers());
            request.getRequestDispatcher("/WEB-INF/view/manager/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String action = request.getParameter("action");

        // Delete branch — remove the user, then reload the list (Post-Redirect-Get).
        if ("delete".equals(action)) {
            String username = request.getParameter("username");
            userService.deleteUser(username);
            response.sendRedirect(request.getContextPath() + "/manager/users");
            return;
        }

        //saving the update
        if("update".equals(action)) {
            String username = request.getParameter("username");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String role = request.getParameter("role");

            Staff staff;
            switch (Role.valueOf(role)) {
                case MANAGER -> staff = new Manager(0, username, null, fullName, email, phone);
                case RECEPTIONIST ->  staff = new Receptionist(0, username, null, fullName, email, phone);
                case DENTIST -> staff = new Dentist(0, username, null, fullName, email, phone, null);
                default -> {return;}
            }

            userService.updateUser(staff);
            response.sendRedirect(request.getContextPath() + "/manager/users");
            return;
        }


        String username = request.getParameter("username");
        String passwordHash = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        Staff staff;
        switch (Role.valueOf(role)) {
            case MANAGER -> staff = new Manager(0, username, null, fullName, email, phone);
            case RECEPTIONIST -> staff = new Receptionist(0, username, null, fullName, email, phone);
            case DENTIST -> staff = new Dentist(0, username, null, fullName, email, phone, null);
            default -> { return; }
        }

        userService.addUser(staff, passwordHash);
        response.sendRedirect(request.getContextPath() + "/manager/users");
    }

}
