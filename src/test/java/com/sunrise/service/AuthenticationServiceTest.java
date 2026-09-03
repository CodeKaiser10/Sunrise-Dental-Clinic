package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.service.AuthenticationService;
import com.sunrisedc.sunrisedentalclinic.dao.UserDAO;
import com.sunrisedc.sunrisedentalclinic.model.Manager;
import com.sunrisedc.sunrisedentalclinic.model.Receptionist;
import com.sunrisedc.sunrisedentalclinic.model.Dentist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Authentication tests — password mechanism (pure) and login (mocked DAO).
public class AuthenticationServiceTest {

    private UserDAO userDAO;
    private AuthenticationService authService;

    // Real Bcrypt hash of "Password@123".
    private static final String HASH_PASSWORD = "$2a$12$6IKQqqDqe6u1kU.JN1I51.pwVaXiqcoLhAKd7VUIbfjv86VvCIjey";

    @BeforeEach
    void setUp() {
        userDAO = mock(UserDAO.class);
        authService = new AuthenticationService(userDAO);   // inject the mock DAO
    }

    //password mechanism

    @Test
    public void testPassword_correct() {
        assertTrue(authService.checkPassword("Password@123", HASH_PASSWORD));
    }

    @Test
    public void testPassword_incorrect() {
        assertFalse(authService.checkPassword("Password", HASH_PASSWORD));
    }

    // login: DAO mocked so no real database is hit

    @Test
    public void testStaff_allRoles() {
        when(userDAO.findByUsername("manager")).thenReturn(new Manager(1, "manager", HASH_PASSWORD, "M", "m@s.lk", "071"));
        when(userDAO.findByUsername("receptionist")).thenReturn(new Receptionist(2, "receptionist", HASH_PASSWORD, "R", "r@s.lk", "072"));
        when(userDAO.findByUsername("dentist")).thenReturn(new Dentist(3, "dentist", HASH_PASSWORD, "D", "d@s.lk", "073", null));

        assertNotNull(authService.login("manager", "Password@123"));
        assertNotNull(authService.login("receptionist", "Password@123"));
        assertNotNull(authService.login("dentist", "Password@123"));
    }

    @Test
    public void testLogin_returnType() {
        when(userDAO.findByUsername("manager")).thenReturn(new Manager(1, "manager", HASH_PASSWORD, "M", "m@s.lk", "071"));
        when(userDAO.findByUsername("receptionist")).thenReturn(new Receptionist(2, "receptionist", HASH_PASSWORD, "R", "r@s.lk", "072"));
        when(userDAO.findByUsername("dentist")).thenReturn(new Dentist(3, "dentist", HASH_PASSWORD, "D", "d@s.lk", "073", null));

        assertInstanceOf(Manager.class, authService.login("manager", "Password@123"));
        assertInstanceOf(Receptionist.class, authService.login("receptionist", "Password@123"));
        assertInstanceOf(Dentist.class, authService.login("dentist", "Password@123"));
    }

    @Test
    public void testLogin_wrongPassword() {
        when(userDAO.findByUsername("manager")).thenReturn(new Manager(1, "manager", HASH_PASSWORD, "M", "m@s.lk", "071"));
        // Wrong password won't match the stored hash null.
        assertNull(authService.login("manager", "Password"));
    }

    @Test
    public void testLogin_userNotFound() {
        // DAO returns null for an unknown user login returns null.
        when(userDAO.findByUsername("none")).thenReturn(null);
        assertNull(authService.login("none", "Password@123"));
    }
}