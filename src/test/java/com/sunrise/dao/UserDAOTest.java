package com.sunrise.dao;

import com.sunrisedc.sunrisedentalclinic.dao.UserDAO;
import com.sunrisedc.sunrisedentalclinic.dao.DBConnectionFactory;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import com.sunrisedc.sunrisedentalclinic.model.Receptionist;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.sql.*;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

public class UserDAOTest {

    @Test
    void FindUserByUsername() throws Exception{
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        String query = "SELECT * FROM users WHERE username = ?";
        when(connection.prepareStatement(query)).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("user_id")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("manager");
        when(resultSet.getString("password_hash")).thenReturn("passwordHash");
        when(resultSet.getString("full_name")).thenReturn("Full_name");
        when(resultSet.getString("email")).thenReturn("manager@sunrise.lk");
        when(resultSet.getString("phone")).thenReturn("0711111111");
        when(resultSet.getString("role")).thenReturn("MANAGER");

        try (MockedStatic<DBConnectionFactory> mocked = mockStatic(DBConnectionFactory.class);) {
            mocked.when(DBConnectionFactory::getConnection).thenReturn(connection);

            UserDAO userDAO = new UserDAO();
            Staff user = userDAO.findByUsername("manager");

            assertNotNull(user);
            assertEquals("manager", user.getUsername());
            verify(statement).setString(1, "manager");
        }
    }

    @Test
    void returnNullWhenUserNotFound() throws Exception{
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        try (MockedStatic<DBConnectionFactory> mocked = mockStatic(DBConnectionFactory.class);) {
            mocked.when(DBConnectionFactory::getConnection).thenReturn(connection);

            UserDAO userDAO = new UserDAO();
            assertNull(userDAO.findByUsername("none"));
        }
    }

    @Test
    void insertUserUsingPreparedStatement() throws Exception{
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);

        String query = "INSERT INTO users (username, password_hash, role, full_name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        Receptionist staff = new Receptionist(0, "recep", "hash", "temp_user", "temp@sunrise.lk", "0711111111");

        try (MockedStatic<DBConnectionFactory> mocked = mockStatic(DBConnectionFactory.class);) {
            mocked.when(DBConnectionFactory::getConnection).thenReturn(connection);

            UserDAO userDAO = new UserDAO();
            userDAO.insert(staff);

            verify(statement).setString(1, "recep");         // username
            verify(statement).setString(2, "hash");          // password hash
            verify(statement).setString(3, "RECEPTIONIST");  // role
            verify(statement).executeUpdate();
        }
    }

    @Test
    void returnAllUsers() throws Exception{
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("user_id")).thenReturn(1, 2);
        when(resultSet.getString("username")).thenReturn("manager","reception");
        when(resultSet.getString("password_hash")).thenReturn("p1", "p2");
        when(resultSet.getString("full_name")).thenReturn("Man", "Rec");
        when(resultSet.getString("email")).thenReturn("m@s.lk", "r@s.lk");
        when(resultSet.getString("phone")).thenReturn("071", "070");
        when(resultSet.getString("role")).thenReturn("MANAGER", "RECEPTIONIST");

        try (MockedStatic<DBConnectionFactory> mocked = mockStatic(DBConnectionFactory.class);) {
            mocked.when(DBConnectionFactory::getConnection).thenReturn(connection);

            UserDAO userDAO = new UserDAO();
            List<Staff> users = userDAO.findAll();

            assertEquals(2, users.size());
            assertEquals("manager", users.get(0).getUsername());
        }
    }
}
