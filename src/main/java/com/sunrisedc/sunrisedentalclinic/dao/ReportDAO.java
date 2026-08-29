package com.sunrisedc.sunrisedentalclinic.dao;

import com.sunrisedc.sunrisedentalclinic.model.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public void insert(Report report) {
        String query = "INSERT INTO reports (type, generate_date, generated_by) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, report.getType());
            statement.setString(2, report.getGenerateDate());
            statement.setInt(3, report.getGeneratedBy());
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

    // Lists all generated reports (with the generator's name), newest first.
    public List<Report> findAll() {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT r.*, u.full_name AS generated_by_name " +
                "FROM reports r JOIN user u ON r.generated_by = u.user_id " +
                "ORDER BY r.generate_date DESC";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Report report = new Report(
                        resultSet.getInt("report_id"),
                        resultSet.getString("type"),
                        resultSet.getString("generate_date"),
                        resultSet.getInt("generated_by")
                );
                report.setGenerateByName(resultSet.getString("generated_by_name"));
                reports.add(report);
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
        return reports;
    }
}
