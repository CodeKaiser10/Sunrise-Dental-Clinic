package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.service.AuthenticationService;
import com.sunrisedc.sunrisedentalclinic.service.UserService;
import com.sunrisedc.sunrisedentalclinic.dao.UserDAO;
import com.sunrisedc.sunrisedentalclinic.model.Receptionist;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

// Unit test for UserService using mocked dependencies (no real database).
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private AuthenticationService authenticationService;

    // Creates the @Mock objects before each test runs.
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        // Script the fake auth service to return a known hash.
        when(authenticationService.hashPassword("password")).thenReturn("hashedPassword");

        // Inject the mocks into the service.
        UserService service = new UserService(userDAO, authenticationService);

        Staff staff = new Receptionist(0, "fake_user", null, "Fake", "fake@sunrise.lk", "0700000000");
        service.addUser(staff, "password");

        // Verify the service hashed the password and passed the user to insert().
        verify(authenticationService).hashPassword("password");
        verify(userDAO).insert(staff);
    }
}