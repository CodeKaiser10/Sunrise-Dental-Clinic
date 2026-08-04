package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.service.AuthenticationService;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import com.sunrisedc.sunrisedentalclinic.model.Manager;
import com.sunrisedc.sunrisedentalclinic.model.Receptionist;
import com.sunrisedc.sunrisedentalclinic.model.Dentist;
import static org.junit.Assert.*;
import org.junit.Test;


//password authentication checking for user login flows
public class AuthenticationServiceTest {

    //BCrypt hash for password
    private static final String HASH_PASSWORD = "$2a$12$6IKQqqDqe6u1kU.JN1I51.pwVaXiqcoLhAKd7VUIbfjv86VvCIjey";

    //password authentication mechanism without database checking
    @Test
    public void testPassword_correct() {
        AuthenticationService auth = AuthenticationService.getInstance();
        assertTrue(auth.checkPassword("Password@123", HASH_PASSWORD));
    }

    @Test
    public void testPassword_incorrect() {
        AuthenticationService auth = AuthenticationService.getInstance();
        assertFalse(auth.checkPassword("Password", HASH_PASSWORD));
    }

    //using the database


    @Test
    public void testStaff_allRoles() {
        AuthenticationService auth = AuthenticationService.getInstance();
        assertNotNull(auth.login("manager", "Password@123"));
        assertNotNull(auth.login("receptionist", "Password@123"));
        assertNotNull(auth.login("dentist", "Password@123"));
    }

    //
    @Test
    public void testLogin_returnType() {
        AuthenticationService auth = AuthenticationService.getInstance();
        assertTrue(auth.login("manager", "Password@123") instanceof Manager);
        assertTrue(auth.login("receptionist", "Password@123") instanceof Receptionist);
        assertTrue(auth.login("dentist", "Password@123") instanceof Dentist);
    }

    @Test
    public void testLogin_wrongPassword() {
        AuthenticationService auth = AuthenticationService.getInstance();
        assertNull(auth.login("manager", "Password"));
    }

    @Test
    public void testLogin_userNotFound() {
        AuthenticationService auth = AuthenticationService.getInstance();
        assertNull(auth.login("none", "Password@123"));
    }
}
