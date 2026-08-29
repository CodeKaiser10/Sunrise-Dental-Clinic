package com.sunrisedc.sunrisedentalclinic.dao;

import com.sunrisedc.sunrisedentalclinic.model.Prescription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    //add a new prescription
    public void insert(Prescription prescription) {
        String query  = "INSERT INTO prescriptions (patient_id, dentist_id, date, medication, dosage, notes) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, prescription.getPatientId());
            statement.setInt(2, prescription.getDentistId());
            statement.setString(3, prescription.getDate());
            statement.setString(4, prescription.getMedication());
            statement.setString(5, prescription.getDosage());
            statement.setString(6, prescription.getNotes());
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

    //list all prescriptions
    public List<Prescription> findByPatient(int patientId) {
        List<Prescription> list = new ArrayList<>();
        String query = "SELECT * FROM prescriptions WHERE patient_id = ? ORDER BY date DESC";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
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
        return list;
    }

    //updates an exiting prescription
    public void update(Prescription prescription) {
        String query = "UPDATE prescriptions SET medication = ?, dosage = ?, notes = ? WHERE prescription_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, prescription.getMedication());
            statement.setString(2, prescription.getDosage());
            statement.setString(3, prescription.getNotes());
            statement.setInt(4, prescription.getPrescriptionId());
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

    private Prescription mapRow(ResultSet resultSet) throws SQLException {
        return new Prescription(
                resultSet.getInt("prescription_id"),
                resultSet.getInt("patient_id"),
                resultSet.getInt("dentist_id"),
                resultSet.getString("date"),
                resultSet.getString("medication"),
                resultSet.getString("dosage"),
                resultSet.getString("notes")
        );
    }
}
