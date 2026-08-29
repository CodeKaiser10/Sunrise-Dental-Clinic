package com.sunrisedc.sunrisedentalclinic.dao;

import com.sunrisedc.sunrisedentalclinic.model.MedicalRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalRecordDAO {

    //finds a patients medical record
    public MedicalRecord findByPatient(int patientId) {
        String query = "SELECT * FROM medical_records WHERE patient_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        }catch (SQLException e) {
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

    //creates a new record for a patient
    public void insert(MedicalRecord record) {
        String query = "INSERT INTO medical_records (patient_id, created_date, notes) VALUES (?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, record.getPatientId());
            statement.setString(2, record.getCreatedDate());
            statement.setString(2, record.getCreatedDate());
            statement.setString(3, record.getNotes());
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

    //updates the notes on existing record
    public void update(MedicalRecord record) {
        String query = "UPDATE medical_records SET notes =  ? WHERE patient_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getNotes());
            statement.setInt(2, record.getPatientId());
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

    //builds a medical record
    private MedicalRecord mapRow(ResultSet resultSet) throws SQLException {
        return new MedicalRecord(
                resultSet.getInt("record_id"),
                resultSet.getInt("patient_id"),
                resultSet.getString("created_date"),
                resultSet.getString("notes")
        );
    }
}
