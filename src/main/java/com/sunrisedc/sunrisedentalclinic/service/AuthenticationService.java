package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.UserDAO;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationService {

    private static AuthenticationService instance;
    private UserDAO userDAO;

    private AuthenticationService() {
        this.userDAO = new UserDAO();
    }

    public static AuthenticationService getInstance() {
        if (instance == null) {
            synchronized (AuthenticationService.class) {
                if (instance == null) {
                    instance = new AuthenticationService();
                }
            }
        }
        return instance;
    }

    public boolean checkPassword (String plainPassword, String HASH_PASSWORD) {
        return BCrypt.checkpw(plainPassword, HASH_PASSWORD);
    }

    public Staff login(String username, String passwordHash) {
        Staff user = userDAO.findByUsername(username);
        if (user == null) {
            return null;
        }
        if (checkPassword(passwordHash, user.getPasswordHash())) {
            return user;
        }
        return null;
    }
}
