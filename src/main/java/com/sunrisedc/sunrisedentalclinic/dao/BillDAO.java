package com.sunrisedc.sunrisedentalclinic.dao;

import com.sunrisedc.sunrisedentalclinic.model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDAO {

    //saves a bill
    public void insert(Bill bill) {
        String query = "INSERT INTO bills (appointment_id, consultation_fee, treatment_fee, discount, total_amount, bill_date) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, bill.getAppointmentId());
            statement.setDouble(2, bill.getConsultationFee());
            statement.setDouble(3, bill.getTreatmentFee());
            statement.setDouble(4, bill.getDiscount());
            statement.setDouble(5, bill.getTotalAmount());
            statement.setString(6, bill.getBillDate());
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

    //finds the for given appointment
    public Bill findByAppointment(int appointmentId) {
        String query = "SELECT * FROM bills WHERE appointment_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, appointmentId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
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
        return null;
    }

    private Bill mapRow(ResultSet resultSet) throws SQLException {
        return new Bill(
                resultSet.getInt("bill_id"),
                resultSet.getInt("appointment_id"),
                resultSet.getDouble("consultation_fee"),
                resultSet.getDouble("treatment_fee"),
                resultSet.getDouble("discount"),
                resultSet.getDouble("total_amount"),
                resultSet.getString("bill_date")
        );
    }
}
