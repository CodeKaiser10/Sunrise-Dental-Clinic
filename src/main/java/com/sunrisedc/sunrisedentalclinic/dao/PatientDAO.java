package com.sunrisedc.sunrisedentalclinic.dao;

import com.sunrisedc.sunrisedentalclinic.model.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    //insert a new patient row
    public void insert(Patient patients) {
        String query = "INSERT INTO patients (name, address, contact_number, date_of_birth, gender) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, patients.getName());
            statement.setString(2, patients.getAddress());
            statement.setString(3, patients.getContactNumber());
            statement.setString(4, patients.getDateOfBirth());
            statement.setString(5, patients.getGender());
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

    //return patients as a list
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patients.add(mapRow(resultSet));
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
        return patients;
    }

    //For find patients name in search
    public List<Patient> searchByName(String name) {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients WHERE name LIKE ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patients.add(mapRow(resultSet));
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
        return patients;
    }

    //finds a patient by ID
    public Patient findById(int patientId) {
        String query = "SELECT * FROM patients WHERE patient_id=?";
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

    //updates an existing patient's details
    public void update(Patient patients) {
        String query = "UPDATE patients SET name = ?, address = ?, contact_number = ?, date_of_birth = ?, gender= ? WHERE patient_id=?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, patients.getName());
            statement.setString(2, patients.getAddress());
            statement.setString(3, patients.getContactNumber());
            statement.setString(4, patients.getDateOfBirth());
            statement.setString(5, patients.getGender());
            statement.setInt(6, patients.getPatientId());
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

    //deletes a patient by id
    public void deleteById(int patientId) {
        String query = "DELETE FROM patients WHERE patient_id=?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);
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

    // Finds a patient by their contact number (used during booking to reuse existing patients).
    public Patient findByContactNumber(String contactNumber) {
        String query = "SELECT * FROM patients WHERE contact_number = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, contactNumber);
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

    // Inserts a new patient and returns the generated patient id.
    public int insertAndReturnId(Patient patient) {
        String query = "INSERT INTO patients (name, address, contact_number, date_of_birth, gender) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet keys = null;
        int newId = 0;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getAddress());
            statement.setString(3, patient.getContactNumber());
            statement.setString(4, patient.getDateOfBirth());
            statement.setString(5, patient.getGender());
            statement.executeUpdate();
            keys = statement.getGeneratedKeys();
            if (keys.next()) {
                newId = keys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (keys != null) keys.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newId;
    }



    //builds a patient object from the current result-set row
    private Patient mapRow(ResultSet resultSet) throws SQLException {
        int  id = resultSet.getInt("patient_id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String contactNumber = resultSet.getString("contact_number");
        String dateOfBirth = resultSet.getString("date_of_birth");
        String gender = resultSet.getString("gender");
        return new Patient(id, name, address, contactNumber, dateOfBirth, gender);
    }
}
