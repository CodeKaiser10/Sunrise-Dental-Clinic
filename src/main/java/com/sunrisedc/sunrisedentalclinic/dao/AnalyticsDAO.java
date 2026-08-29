package com.sunrisedc.sunrisedentalclinic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//read only aggregate queries for the analytics page
public class AnalyticsDAO {

    //counts all rows in patient table
    public int countPatients() {
        return runCount("SELECT COUNTS(*) FROM patients");
    }

    //counts all appointments
    public int countAppointments() {
        return runCount("SELECT COUNT(*) FROM appointments");
    }

    //counts appointments in given status
    public int countAppointmentsByStatus(String status) {
        String query = "SELECT COUNT(*) FROM appointment WHERE appointment_status = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, status);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet, statement);
        }
        return 0;
    }

    //sums the total amount of every bill
    public double sumRevenue() {
        String query = "SELECT COALESCE(SUM(total_amount), 0) FROM bill";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet, statement);
        }
        return 0;
    }

    // Shared helper for the simple no-parameter COUNT queries.
    private int runCount(String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet, statement);
        }
        return 0;
    }

    // Shared close helper.
    private void close(ResultSet resultSet, PreparedStatement statement) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

