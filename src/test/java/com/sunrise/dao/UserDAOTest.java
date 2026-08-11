package com.sunrise.dao;

import com.sunrisedc.sunrisedentalclinic.dao.UserDAO;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

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

    @Test
    public void testFindAll() {
        UserDAO userDAO = new UserDAO();
        List<Staff> users = userDAO.findAll();

        assertNotNull(users);
        assertTrue(users.size() >= 3);
    }
}
