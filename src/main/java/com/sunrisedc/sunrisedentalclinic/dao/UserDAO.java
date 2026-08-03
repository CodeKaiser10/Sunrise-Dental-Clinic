package com.sunrisedc.sunrisedentalclinic.dao;

import com.sunrisedc.sunrisedentalclinic.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    public Staff findByUsername(String username) {
        String query = "SELECT * FROM users STAFF WHERE username = ?";

        try {
            Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                int id = resultSet.getInt("user_id");
                String user = resultSet.getString("username");
                String hash = resultSet.getString("password_hash");
                String name = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Role role = Role.valueOf(resultSet.getString("role"));

                switch (role){
                    case MANAGER:
                        return new Manager(id, user, hash, name, email, phone);
                    case RECEPTIONIST:
                        return new Receptionist(id, user, hash, name, email, phone);
                    case DENTIST:
                        String spec = resultSet.getString("specialization");
                        return new Dentist(id, user, hash, name, email, phone, spec);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
