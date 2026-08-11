package com.sunrisedc.sunrisedentalclinic.dao;

import com.sunrisedc.sunrisedentalclinic.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Isolate all user tables in mySQL (DAO Pattern and Single-responsibility)
public class UserDAO {

    //returns the user as a Staff or a null
    public Staff findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //shared Singleton connection through factory wrapper
            connection  = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            //pull each column value out of the row
            if (resultSet.next()){
                int id = resultSet.getInt("user_id");
                String user = resultSet.getString("username");
                String hash = resultSet.getString("password_hash");
                String name = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Role role = Role.valueOf(resultSet.getString("role"));

                //factory logic for returning a staff
                switch (role){
                    case MANAGER:
                        return new Manager(id, user, hash, name, email, phone);
                    case RECEPTIONIST:
                        return new Receptionist(id, user, hash, name, email, phone);
                    case DENTIST:
                        return new Dentist(id, user, hash, name, email, phone, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (resultSet != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //Returns every user in table as a list of objects
    public List<Staff> findAll() {
        List<Staff> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            //Loop every row
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String user = resultSet.getString("username");
                String passwordHash = resultSet.getString("password_hash");
                String name = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Role role = Role.valueOf(resultSet.getString("role"));

                switch (role) {
                    case MANAGER:
                        users.add(new Manager(id, user, passwordHash, name, email, phone));
                        break;
                    case RECEPTIONIST:
                        users.add(new Receptionist(id, user, passwordHash, name, email, phone));
                        break;
                    case DENTIST:
                        users.add(new Dentist(id, user, passwordHash ,name, email, phone, null));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    //insert a new staff row
    public void insert(Staff staff) {

        String query = "INSERT INTO users (username, password_hash, role ,full_name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection .prepareStatement(query);
            statement.setString(1, staff.getUsername());
            statement.setString(2, staff.getPasswordHash());
            statement.setString(3, staff.getRole().name());
            statement.setString(4, staff.getFullName());
            statement.setString(5, staff.getEmail());
            statement.setString(6, staff.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //deletes user by username
    public void deleteByUsername(String username) {
        String query = "DELETE FROM users WHERE username = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


