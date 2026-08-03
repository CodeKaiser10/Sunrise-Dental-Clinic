package com.sunrisedc.sunrisedentalclinic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//using Singleton pattern
public class DBConnection {

    private static final String DB_Url = "jdbc:mysql://127.0.0.1:3306/sunrise_dental";
    private static final String DB_User = "root";
    private static final String DB_Password = "admin";

    //single shared instance
    private static DBConnection instance;
    private Connection connection;

    //the private constructor that blocks instance from outside access
    private DBConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_Url, DB_User, DB_Password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    //double-checked locking for thread safety
    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
