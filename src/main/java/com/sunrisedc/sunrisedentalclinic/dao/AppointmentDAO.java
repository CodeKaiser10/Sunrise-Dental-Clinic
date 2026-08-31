package com.sunrisedc.sunrisedentalclinic.dao;

import com.sunrisedc.sunrisedentalclinic.model.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//isolate all appointment-table SQL
public class AppointmentDAO {

    private static final String BASE_SELECT =
            "SELECT a.*, p.name AS patient_name, u.full_name AS dentist_name " +
                    "FROM appointments a " +
                    "JOIN patients p ON a.patient_id = p.patient_id " +
                    "JOIN users u ON a.dentist_id = u.user_id ";


    //books a new appointment
    public void insert(Appointment appointment) {
        String query = "INSERT INTO appointments (appointment_number, patient_id, dentist_id, appointment_datetime, appointment_status) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, appointment.getAppointmentNumber());
            statement.setInt(2, appointment.getPatientId());
            statement.setInt(3, appointment.getDentistId());
            statement.setString(4, appointment.getAppointmentDateTime());
            statement.setString(5, appointment.getAppointmentStatus());
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

    //returns every appointment with names
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        String query = BASE_SELECT + "ORDER BY a.appointment_datetime DESC";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                appointments.add(mapRow(resultSet));
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
        return appointments;
    }

    //finds one appointment by its number
    public Appointment findByNumber (String appointmentNumber) {
        String query = BASE_SELECT + "WHERE a.appointment_number = ?";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, appointmentNumber);
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

    //changes an appointment status
    public void updateStatus (int appointmentId, String status) {
        String query = "UPDATE appointments SET appointment_status = ? WHERE appointment_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, appointmentId);
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

    //builds an appointment
    private Appointment mapRow(ResultSet resultSet) throws SQLException {
        Appointment appointment = new Appointment(
                resultSet.getInt("appointment_Id"),
                resultSet.getString("appointment_number"),
                resultSet.getInt("patient_id"),
                resultSet.getInt("dentist_id"),
                resultSet.getString("appointment_datetime"),
                resultSet.getString("appointment_status")
        );
        appointment.setPatientName(resultSet.getString("patient_name"));
        appointment.setDentistName(resultSet.getString("dentist_name"));
        return appointment;
    }
}
