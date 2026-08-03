package com.sunrise.dao;

import com.sunrisedc.sunrisedentalclinic.dao.UserDAO;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import static org.junit.Assert.*;
import org.junit.Test;

public class UserDAOTest {

    @Test
    public void testFindByUsername_found() {
        UserDAO userDAO = new UserDAO();
        Staff user = userDAO.findByUsername("manager");
        assertNotNull(user);
        assertEquals("manager", user.getUsername());
    }

    @Test
    public void testFindByUsername_notfound() {
        UserDAO userDAO = new UserDAO();
        assertNull(userDAO.findByUsername("not found"));
    }
}
