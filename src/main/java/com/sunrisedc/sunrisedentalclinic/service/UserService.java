package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.UserDAO;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import java.util.List;


public class UserService {

    private static UserService instance;
    private UserDAO userDAO;
    private AuthenticationService authenticationService;

    private UserService() {
        userDAO = new UserDAO();
        this.authenticationService = AuthenticationService.getInstance();
    }

    public UserService(UserDAO userDAO, AuthenticationService authenticationService) {
        this.userDAO = userDAO;
        this.authenticationService = authenticationService;
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public void addUser(Staff staff, String plainPassword) {
        staff.setPasswordHash(authenticationService.hashPassword(plainPassword));
        userDAO.insert(staff);
    }

    public List<Staff> getAllUsers() {
        return userDAO.findAll();
    }
}
